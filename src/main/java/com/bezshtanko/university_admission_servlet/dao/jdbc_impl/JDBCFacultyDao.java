package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.interfaces.FacultyDao;
import com.bezshtanko.university_admission_servlet.dao.mapper.FacultyMapper;
import com.bezshtanko.university_admission_servlet.dao.mapper.SubjectMapper;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JDBCFacultyDao extends JDBCDao implements FacultyDao {

    private static final Logger log = LoggerFactory.getLogger(JDBCFacultyDao.class);

    public JDBCFacultyDao(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Faculty entity) {
    }

    @Override
    public Faculty findById(Integer id) {
        return null;
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
            log.info("prepared statement for finding all faculties created");
            ResultSet resultSet = ps.executeQuery();
            log.info("query successfully executed");

            log.info("faculties mapping started");
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Faculty entity) {
    }

    @Override
    public void deleteById(Integer id) {
    }

    @Override
    public Optional<Faculty> getByIdWithEnrollments(Long id) {
        return null;
    }

}
