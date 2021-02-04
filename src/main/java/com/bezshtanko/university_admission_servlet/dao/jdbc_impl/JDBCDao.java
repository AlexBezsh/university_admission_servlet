package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JDBCDao {

    private static final Logger log = LoggerFactory.getLogger(JDBCDao.class);

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

    protected void handleConnectionAfterException(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            log.error("Exception occurred during connection rollback execution");
        }
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error("An attempt to set connection in auto commit mode failed");
        }
    }

    protected boolean updateEntityStatus(Long entityId, String query) {
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, entityId);
            int affectedRows = ps.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            log.error("SQLException occurred during status update");
            throw new RuntimeException(e);
        }
    }

}
