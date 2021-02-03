package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.interfaces.FacultyDao;
import com.bezshtanko.university_admission_servlet.dao.mapper.FacultyMapper;
import com.bezshtanko.university_admission_servlet.dao.mapper.SubjectMapper;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;
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
                }
                else {
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
            }
            log.info("Faculty {} have been successfully saved", faculty);
        } catch (SQLException e) {
            log.error("Exception occurred during saving new faculty");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Exception occurred during connection rollback execution");
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                log.error("An attempt to set connection in auto commit mode failed");
            }
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
    public void update(Faculty entity) {
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting faculty with id '{}'", id);
        String query = "DELETE FROM faculty WHERE faculty.id = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException occurred during deleting faculty with id {}'", id);
            throw new RuntimeException(e);
        }
    }

}
