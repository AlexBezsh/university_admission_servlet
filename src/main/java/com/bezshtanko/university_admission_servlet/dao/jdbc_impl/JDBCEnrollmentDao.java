package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.interfaces.EnrollmentDao;
import com.bezshtanko.university_admission_servlet.dao.mapper.EnrollmentMapper;
import com.bezshtanko.university_admission_servlet.dao.mapper.FacultyMapper;
import com.bezshtanko.university_admission_servlet.dao.mapper.MarkMapper;
import com.bezshtanko.university_admission_servlet.dao.mapper.UserMapper;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.enrollment.EnrollmentStatus;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.mark.Mark;
import com.bezshtanko.university_admission_servlet.model.user.User;
import com.bezshtanko.university_admission_servlet.model.user.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class JDBCEnrollmentDao extends JDBCDao implements EnrollmentDao {

    private static final Logger log = LoggerFactory.getLogger(JDBCEnrollmentDao.class);

    public JDBCEnrollmentDao(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Enrollment enrollment) {
        String insertEnrollment = "INSERT INTO enrollment(user_id, faculty_id, status) VALUES(?, ?, ?)";
        try (PreparedStatement insertEnrollmentStmt = connection.prepareStatement(insertEnrollment, Statement.RETURN_GENERATED_KEYS)) {
            insertEnrollmentStmt.setLong(1, enrollment.getUser().getId());
            insertEnrollmentStmt.setLong(2, enrollment.getFaculty().getId());
            insertEnrollmentStmt.setString(3, enrollment.getStatus().toString());
            log.info("Prepared statement created for new enrollment: {}", enrollment);

            connection.setAutoCommit(false);
            log.info("Transaction has been opened");
            int affectedRows = insertEnrollmentStmt.executeUpdate();
            log.info("Insert new enrollment query successfully executed.");
            if (affectedRows == 0) {
                log.error("Saving enrollment failed, no rows affected.");
            }

            try (ResultSet generatedKeys = insertEnrollmentStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    enrollment.setId(generatedKeys.getLong(1));
                }
                else {
                    log.error("Saving enrollment failed, no ID obtained.");
                }
            }

            StringBuilder insertMarks = new StringBuilder("INSERT INTO marks(enrollment_id, subject_id, mark) VALUES");
            enrollment.getMarks().forEach(m ->
                    insertMarks.append('(').append(enrollment.getId()).append(',').append(' ')
                            .append(m.getSubject().getId()).append(',').append(' ')
                            .append(m.getMark()).append("), "));
            insertMarks.setLength(insertMarks.length() - 2);

            try (PreparedStatement insertMarksStmt = connection.prepareStatement(insertMarks.toString())) {
                log.info("Saving enrollment marks");
                insertMarksStmt.execute();
                connection.commit();
                connection.setAutoCommit(true);
            }
            log.info("Enrollment {} have been successfully saved", enrollment);
        } catch (SQLException e) {
            log.error("Exception occurred during saving new enrollment");
            handleConnectionAfterException(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean setApproved(Long id) {
        return updateStatus(id, EnrollmentStatus.APPROVED);
    }

    @Override
    public Optional<Enrollment> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Enrollment> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Enrollment> findAllByUserId(Long id) {
        String query =
                "SELECT enrollment.id AS e_id, " +
                    "enrollment.status AS e_status, " +
                    "faculty.id AS f_id, " +
                    "faculty.name_en AS f_name_en, " +
                    "faculty.name_ua AS f_name_ua, " +
                    "faculty.status AS f_status, " +
                    "description_en, " +
                    "description_ua, " +
                    "state_funded_places, " +
                    "contract_places, " +
                    "marks.id AS m_id, " +
                    "marks.mark " +
                "FROM enrollment " +
                "JOIN faculty ON enrollment.faculty_id = faculty.id " +
                "JOIN marks ON enrollment.id = marks.enrollment_id " +
                "WHERE enrollment.user_id = " + id;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            log.info("Prepared statement for finding all enrollments for user with id '{}' created", id);

            ResultSet resultSet = ps.executeQuery();
            log.info("Query successfully executed");

            log.info("Enrollments mapping started");
            Map<Long, Enrollment> enrollments = new HashMap<>();
            Map<Long, Faculty> faculties = new HashMap<>();
            Map<Long, Mark> marks = new HashMap<>();

            EnrollmentMapper enrollmentMapper = new EnrollmentMapper();
            FacultyMapper facultyMapper = new FacultyMapper();
            MarkMapper markMapper = new MarkMapper();

            Enrollment enrollment;
            Faculty faculty;
            Mark mark;
            while (resultSet.next()) {
                enrollment = enrollmentMapper.get(resultSet);
                faculty = facultyMapper.get(resultSet);
                mark = markMapper.get(resultSet);

                enrollment = enrollmentMapper.makeUnique(enrollments, enrollment);
                faculty = facultyMapper.makeUnique(faculties, faculty);
                mark = markMapper.makeUnique(marks, mark);

                enrollment.setFaculty(faculty);
                enrollment.getMarks().add(mark);
            }
            return new ArrayList<>(enrollments.values());
        } catch (SQLException e) {
            log.error("SQLException occurred during getting all enrollments of user with id '{}'", id);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Enrollment> findAllRelevantByFacultyId(Long facultyId) {
        String query =
                "SELECT enrollment.id AS e_id, " +
                    "enrollment.status AS e_status, " +
                    "user.id AS u_id, " +
                    "user.full_name, " +
                    "user.email, " +
                    "user.password, " +
                    "user.status AS u_status, " +
                    "user.city, " +
                    "user.region, " +
                    "user.education, " +
                    "'ENTRANT' AS ROLES, " +
                    "marks.id AS m_id, " +
                    "marks.mark " +
                "FROM enrollment " +
                "JOIN user ON enrollment.user_id = user.id " +
                "JOIN marks ON enrollment.id = marks.enrollment_id " +
                "WHERE enrollment.faculty_id = " + facultyId + " " +
                "AND (user.status = '" + UserStatus.ACTIVE + "' " +
                        "OR user.status = '" + UserStatus.BLOCKED + "')";
        return findAllWithUsers(query);
    }

    @Override
    public List<Enrollment> findAllFinalizedByFacultyId(Long facultyId) {
        String query =
                "SELECT enrollment.id AS e_id, " +
                    "enrollment.status AS e_status, " +
                    "user.id AS u_id, " +
                    "user.full_name, " +
                    "user.email, " +
                    "user.password, " +
                    "user.status AS u_status, " +
                    "user.city, " +
                    "user.region, " +
                    "user.education, " +
                    "'ENTRANT' AS ROLES, " +
                    "marks.id AS m_id, " +
                    "marks.mark " +
                "FROM enrollment " +
                "JOIN user ON enrollment.user_id = user.id " +
                "JOIN marks ON enrollment.id = marks.enrollment_id " +
                "WHERE enrollment.faculty_id = " + facultyId + " " +
                "AND enrollment.status = '" + EnrollmentStatus.FINALIZED + "'";
        return findAllWithUsers(query);
    }

    @Override
    public void update(Enrollment entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException();
    }

    private boolean updateStatus(Long enrollmentId, EnrollmentStatus status) {
        log.info("Setting status '{}' to enrollment with id '{}'", status, enrollmentId);
        String query = "UPDATE enrollment SET status = '" + status + "' WHERE id = ?";
        return updateEntityStatus(enrollmentId, query);
    }

    private List<Enrollment> findAllWithUsers(String query) {
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            log.info("Prepared statement for finding enrollments created");

            ResultSet resultSet = ps.executeQuery();
            log.info("Query successfully executed");

            log.info("Enrollments mapping started");
            Map<Long, Enrollment> enrollments = new HashMap<>();
            Map<Long, User> users = new HashMap<>();
            Map<Long, Mark> marks = new HashMap<>();

            EnrollmentMapper enrollmentMapper = new EnrollmentMapper();
            UserMapper userMapper = new UserMapper();
            MarkMapper markMapper = new MarkMapper();

            Enrollment enrollment;
            User user;
            Mark mark;
            while (resultSet.next()) {
                enrollment = enrollmentMapper.get(resultSet);
                user = userMapper.get(resultSet);
                mark = markMapper.get(resultSet);

                enrollment = enrollmentMapper.makeUnique(enrollments, enrollment);
                user = userMapper.makeUnique(users, user);
                mark = markMapper.makeUnique(marks, mark);

                enrollment.setUser(user);
                enrollment.getMarks().add(mark);
            }
            return new ArrayList<>(enrollments.values());
        } catch (SQLException e) {
            log.error("SQLException occurred during getting enrollments");
            throw new RuntimeException(e);
        }
    }
}
