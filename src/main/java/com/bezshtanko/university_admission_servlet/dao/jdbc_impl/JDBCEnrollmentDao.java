package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.interfaces.EnrollmentDao;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class JDBCEnrollmentDao extends JDBCDao implements EnrollmentDao {


    public JDBCEnrollmentDao(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Enrollment entity) {

    }

    @Override
    public Enrollment findById(Integer id) {
        return null;
    }

    @Override
    public List<Enrollment> findAll() {
        return null;
    }

    @Override
    public void update(Enrollment entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }

}
