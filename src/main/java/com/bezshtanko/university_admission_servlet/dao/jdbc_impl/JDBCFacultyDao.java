package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.interfaces.FacultyDao;
import com.bezshtanko.university_admission_servlet.dao.mapper.FacultyMapper;
import com.bezshtanko.university_admission_servlet.dao.mapper.SubjectMapper;
import com.bezshtanko.university_admission_servlet.model.enrollment.EnrollmentStatus;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.faculty.FacultyStatus;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;
import com.bezshtanko.university_admission_servlet.model.user.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class JDBCFacultyDao extends JDBCDao implements FacultyDao {

    private static final Logger log = LoggerFactory.getLogger(JDBCFacultyDao.class);

    public JDBCFacultyDao(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Faculty faculty) {
        String saveFacultyQuery =
                "INSERT INTO faculty(name_en, name_ua, status, description_en, description_ua, state_funded_places, contract_places) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement saveFacultyStmt = connection.prepareStatement(saveFacultyQuery, Statement.RETURN_GENERATED_KEYS)) {
            saveFacultyStmt.setString(1, faculty.getNameEn());
            saveFacultyStmt.setString(2, faculty.getNameUa());
            saveFacultyStmt.setString(3, faculty.getStatus().toString());
            saveFacultyStmt.setString(4, faculty.getDescriptionEn());
            saveFacultyStmt.setString(5, faculty.getDescriptionUa());
            saveFacultyStmt.setInt(6, faculty.getStateFundedPlaces());
            saveFacultyStmt.setInt(7, faculty.getContractPlaces());
            log.info("Prepared statement created for new faculty: {}", faculty);

            connection.setAutoCommit(false);
            log.info("Transaction has been opened");
            int affectedRows = saveFacultyStmt.executeUpdate();
            log.info("Insert new faculty query successfully executed.");
            if (affectedRows == 0) {
                log.error("Saving faculty failed, no rows affected.");
            }

            try (ResultSet generatedKeys = saveFacultyStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    faculty.setId(generatedKeys.getLong(1));
                } else {
                    log.error("Saving faculty failed, no ID obtained.");
                }
            }

            StringBuilder insertFacultySubjectsRelation = new StringBuilder("INSERT INTO faculty_subjects(faculty_id, subjects_id) VALUES");
            faculty.getSubjects().forEach(s ->
                    insertFacultySubjectsRelation
                            .append('(').append(faculty.getId()).append(',').append(' ')
                            .append(s.getId()).append("), "));
            insertFacultySubjectsRelation.setLength(insertFacultySubjectsRelation.length() - 2);

            try (PreparedStatement saveFacultySubjectsRelationStmt = connection.prepareStatement(insertFacultySubjectsRelation.toString())) {
                log.info("Saving faculty-subjects relation");
                saveFacultySubjectsRelationStmt.execute();
                connection.commit();
                connection.setAutoCommit(true);
            }
            log.info("Faculty {} have been successfully saved", faculty);
        } catch (SQLException e) {
            log.error("Exception occurred during saving new faculty");
            handleConnectionAfterException(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Faculty> findById(Long id) {
        String query = "SELECT faculty.id AS f_id, " +
                "faculty.name_en AS f_name_en, " +
                "faculty.name_ua AS f_name_ua, " +
                "faculty.status AS f_status, " +
                "description_en, " +
                "description_ua, " +
                "state_funded_places, " +
                "contract_places, " +
                "subject.id AS s_id, " +
                "subject.type AS s_type, " +
                "subject.name_en AS s_name_en, " +
                "subject.name_ua AS s_name_ua " +
                "FROM faculty " +
                "JOIN faculty_subjects ON faculty.id = faculty_subjects.faculty_id " +
                "JOIN subject ON faculty_subjects.subjects_id = subject.id " +
                "WHERE faculty.id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            log.info("Prepared statement for finding faculty created");
            return Optional.of(findFaculties(ps).get(0));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Faculty> findAll() {
        String query = "SELECT faculty.id AS f_id, " +
                "faculty.name_en AS f_name_en, " +
                "faculty.name_ua AS f_name_ua, " +
                "faculty.status AS f_status, " +
                "description_en, " +
                "description_ua, " +
                "state_funded_places, " +
                "contract_places, " +
                "subject.id AS s_id, " +
                "subject.type AS s_type, " +
                "subject.name_en AS s_name_en, " +
                "subject.name_ua AS s_name_ua " +
                "FROM faculty " +
                "JOIN faculty_subjects ON faculty.id = faculty_subjects.faculty_id " +
                "JOIN subject ON faculty_subjects.subjects_id = subject.id";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            log.info("Prepared statement for finding all faculties created");
            return findFaculties(ps);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Faculty> findFaculties(PreparedStatement ps) throws SQLException {
        ResultSet resultSet = ps.executeQuery();
        log.info("Query successfully executed");

        log.info("Mapping started");
        Map<Long, Faculty> faculties = new HashMap<>();
        Map<Long, Subject> subjects = new HashMap<>();

        FacultyMapper facultyMapper = new FacultyMapper();
        SubjectMapper subjectMapper = new SubjectMapper();

        Faculty faculty;
        Subject subject;
        while (resultSet.next()) {
            faculty = facultyMapper.get(resultSet);
            subject = subjectMapper.get(resultSet);
            faculty = facultyMapper.makeUnique(faculties, faculty);
            subject = subjectMapper.makeUnique(subjects, subject);
            faculty.getSubjects().add(subject);
        }
        return new ArrayList<>(faculties.values());
    }


    @Override
    public void update(Faculty faculty) {
        log.info("Updating faculty with id '{}'", faculty.getId());
        String query = "UPDATE faculty " +
                "SET name_en = ?, name_ua = ?, description_en = ?, description_ua = ?, state_funded_places = ?, contract_places = ? " +
                "WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, faculty.getNameEn());
            ps.setString(2, faculty.getNameUa());
            ps.setString(3, faculty.getDescriptionEn());
            ps.setString(4, faculty.getDescriptionUa());
            ps.setInt(5, faculty.getStateFundedPlaces());
            ps.setInt(6, faculty.getContractPlaces());
            ps.setLong(7, faculty.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException occurred during updating faculty with id {}'", faculty.getId());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean setClosed(Long id) {
        log.info("Closing faculty with id '{}'", id);
        String query = "UPDATE faculty SET status = '" + FacultyStatus.CLOSED + "' WHERE id = ?";
        return updateEntityStatus(id, query);
    }

    @Override
    public void finalizeFaculty(Long facultyId) {
        String getFacultyInfoQuery = "SELECT status, state_funded_places, contract_places FROM faculty WHERE id = ?";
        String closeFacultyQuery = "UPDATE faculty SET status = '" + FacultyStatus.CLOSED + "' WHERE id = ?";
        String finalizeEnrollmentsQuery =
                "UPDATE enrollment " +
                        "SET status = '" + EnrollmentStatus.FINALIZED + "' " +
                        "WHERE id in(" +
                        "SELECT t.e_id from (" +
                            "SELECT enrollment.id as e_id, sum(mark) AS total FROM enrollment " +
                            "JOIN marks ON enrollment.id = marks.enrollment_id " +
                            "JOIN user ON enrollment.user_id = user.id " +
                            "WHERE enrollment.faculty_id = ? " +
                            "AND user.status = '" + UserStatus.ACTIVE + "' " +
                            "AND enrollment.status = '" + EnrollmentStatus.APPROVED + "' " +
                            "GROUP BY enrollment.id " +
                            "ORDER BY total DESC " +
                            "LIMIT ?) t)";
        String setEnrolledStateFundedQuery =
                "UPDATE user " +
                        "SET status = '" + UserStatus.ENROLLED_STATE_FUNDED + "' " +
                        "WHERE id in(" +
                            "SELECT t.u_id from (" +
                                "SELECT user.id as u_id, sum(mark) AS total FROM enrollment " +
                                "JOIN marks ON enrollment.id = marks.enrollment_id " +
                                "JOIN user ON enrollment.user_id = user.id " +
                                "WHERE enrollment.faculty_id = ? " +
                                "AND user.status = '" + UserStatus.ACTIVE + "' " +
                                "AND enrollment.status = '" + EnrollmentStatus.FINALIZED + "' " +
                                "GROUP BY enrollment.id " +
                                "ORDER BY total DESC " +
                                "LIMIT ?) t)";
        String setEnrolledContractQuery =
                "UPDATE user " +
                        "SET status = '" + UserStatus.ENROLLED_CONTRACT + "' " +
                        "WHERE id in(" +
                            "SELECT t.u_id from (" +
                                "SELECT user.id as u_id, sum(mark) AS total FROM enrollment " +
                                "JOIN marks ON enrollment.id = marks.enrollment_id " +
                                "JOIN user ON enrollment.user_id = user.id " +
                                "WHERE enrollment.faculty_id = ? " +
                                "AND user.status = '" + UserStatus.ACTIVE + "' " +
                                "AND enrollment.status = '" + EnrollmentStatus.FINALIZED + "' " +
                                "GROUP BY enrollment.id " +
                                "ORDER BY total DESC " +
                                "LIMIT ?) t)";

        try (PreparedStatement getFacultyInfoStmt = connection.prepareStatement(getFacultyInfoQuery);
             PreparedStatement closeFacultyStmt = connection.prepareStatement(closeFacultyQuery);
             PreparedStatement finalizeEnrollmentsStmt = connection.prepareStatement(finalizeEnrollmentsQuery);
             PreparedStatement setEnrolledStateFundedStmt = connection.prepareStatement(setEnrolledStateFundedQuery);
             PreparedStatement setEnrolledContractStmt = connection.prepareStatement(setEnrolledContractQuery)) {
            log.info("All prepared statements for finalization are created");

            connection.setAutoCommit(false);
            log.info("Transaction opened");

            getFacultyInfoStmt.setLong(1, facultyId);
            ResultSet facultyInfo = getFacultyInfoStmt.executeQuery();
            if (!facultyInfo.next() || FacultyStatus.valueOf(facultyInfo.getString("status")) == FacultyStatus.CLOSED) {
                log.error("Faculty not found or it is closed already");
                throw new SQLException();
            }
            int stateFundedPlaces = facultyInfo.getInt("state_funded_places");
            int contractPlaces = facultyInfo.getInt("contract_places");
            log.info("State funded and contract places quantity received");

            closeFacultyStmt.setLong(1, facultyId);
            closeFacultyStmt.executeUpdate();
            log.info("Faculty was successfully closed");

            finalizeEnrollmentsStmt.setLong(1, facultyId);
            finalizeEnrollmentsStmt.setInt(2, stateFundedPlaces + contractPlaces);
            finalizeEnrollmentsStmt.executeUpdate();
            log.info("Enrollments were finalized");

            setEnrolledStateFundedStmt.setLong(1, facultyId);
            setEnrolledStateFundedStmt.setInt(2, stateFundedPlaces);
            setEnrolledStateFundedStmt.executeUpdate();
            log.info("Users were enrolled on state funded places");

            setEnrolledContractStmt.setLong(1, facultyId);
            setEnrolledContractStmt.setInt(2, contractPlaces);
            setEnrolledContractStmt.executeUpdate();
            log.info("Users were enrolled on contract places");

            connection.commit();
            log.info("Transaction was successfully committed");
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error("Exception occurred during faculty finalization");
            handleConnectionAfterException(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting faculty with id '{}'", id);
        String query = "DELETE FROM faculty WHERE faculty.id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException occurred during deleting faculty with id {}'", id);
            throw new RuntimeException(e);
        }
    }

}
