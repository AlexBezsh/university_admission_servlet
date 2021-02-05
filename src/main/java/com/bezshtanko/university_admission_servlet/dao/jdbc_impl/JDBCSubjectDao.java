package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.interfaces.SubjectDao;
import com.bezshtanko.university_admission_servlet.dao.mapper.SubjectMapper;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCSubjectDao extends JDBCDao implements SubjectDao {

    private static final Logger log = LoggerFactory.getLogger(JDBCSubjectDao.class);

    public JDBCSubjectDao(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Subject entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Subject> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Subject> findAll() {
        String query = "SELECT id AS s_id, " +
                "TYPE AS s_type, " +
                "name_en AS s_name_en, " +
                "name_ua AS s_name_ua " +
                "FROM subject";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            log.info("Prepared statement for finding all subjects created");
            ResultSet resultSet = ps.executeQuery();
            log.info("Query for finding all subjects has been successfully executed");

            SubjectMapper subjectMapper = new SubjectMapper();
            List<Subject> subjects = new ArrayList<>();
            while (resultSet.next()) {
                subjects.add(subjectMapper.get(resultSet));
            }
            return subjects;
        } catch (SQLException e) {
            log.error("Exception occurred during find all subjects query execution");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Subject entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException();
    }

}
