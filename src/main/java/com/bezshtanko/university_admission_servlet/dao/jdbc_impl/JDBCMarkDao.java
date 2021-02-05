package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.interfaces.MarkDao;
import com.bezshtanko.university_admission_servlet.model.mark.Mark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JDBCMarkDao extends JDBCDao implements MarkDao {

    private static final Logger log = LoggerFactory.getLogger(JDBCMarkDao.class);

    public JDBCMarkDao(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Mark entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Mark> findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Mark> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Mark entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException();
    }

}
