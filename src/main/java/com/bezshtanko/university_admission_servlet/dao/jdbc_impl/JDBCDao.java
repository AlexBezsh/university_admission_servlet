package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class JDBCDao implements AutoCloseable {

    protected Connection connection;

    public JDBCDao(Connection connection) {
        this.connection = connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
