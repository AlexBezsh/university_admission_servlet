package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.DataSourceHolder;
import com.bezshtanko.university_admission_servlet.dao.interfaces.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class JDBCDaoFactory extends DaoFactory {

    private static volatile JDBCDaoFactory instance;

    private final DataSource dataSource = DataSourceHolder.getDataSource();

    private JDBCDaoFactory() {
    }

    public static DaoFactory getInstance() {
        JDBCDaoFactory result = instance;
        if (result != null) {
            return result;
        }

        synchronized (JDBCDaoFactory.class) {
            if (instance == null) {
                instance = new JDBCDaoFactory();
            }
            return instance;
        }
    }

    @Override
    public EnrollmentDao createEnrollmentDao() {
        return new JDBCEnrollmentDao(getConnection());
    }

    @Override
    public FacultyDao createFacultyDao() {
        return new JDBCFacultyDao(getConnection());
    }

    @Override
    public MarkDao createMarkDao() {
        return new JDBCMarkDao(getConnection());
    }

    @Override
    public SubjectDao createSubjectDao() {
        return new JDBCSubjectDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
