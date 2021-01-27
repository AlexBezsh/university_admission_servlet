package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.interfaces.SubjectDao;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;

import java.sql.Connection;
import java.util.Set;

public class JDBCSubjectDao extends JDBCDao implements SubjectDao {

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
    public Set<Subject> findAll() {
        return null;
    }

    @Override
    public void update(Subject entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }

}
