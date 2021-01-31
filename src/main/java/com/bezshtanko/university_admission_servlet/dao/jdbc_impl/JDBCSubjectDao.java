package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.interfaces.SubjectDao;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class JDBCSubjectDao extends JDBCDao implements SubjectDao {

    private static final Logger log = LoggerFactory.getLogger(JDBCSubjectDao.class);

    public JDBCSubjectDao(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Subject entity) {

    }

    @Override
    public Subject findById(Integer id) {
        return null;
    }

    @Override
    public List<Subject> findAll() {
        return null;
    }

    @Override
    public void update(Subject entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }

}
