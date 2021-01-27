package com.bezshtanko.university_admission_servlet.dao;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public final class DataSourceHolder {

    private static volatile DataSource dataSource;

    private DataSourceHolder() {
    }

    public static DataSource getDataSource() {
        DataSource result = dataSource;
        if (dataSource != null) {
            return result;
        }

        synchronized (DataSourceHolder.class) {
            if (dataSource == null) {
                BasicDataSource ds = new BasicDataSource();
                ds.setUrl("jdbc:mysql://localhost:3306/university_admission");
                ds.setUsername("root");
                ds.setPassword("root");
                ds.setMinIdle(5);
                ds.setMaxIdle(10);
                ds.setMaxOpenPreparedStatements(100);
                dataSource = ds;
            }
        }
        return dataSource;
    }

}
