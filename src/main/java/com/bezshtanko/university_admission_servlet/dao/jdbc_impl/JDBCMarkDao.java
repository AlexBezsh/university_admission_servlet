package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.interfaces.MarkDao;
import com.bezshtanko.university_admission_servlet.model.mark.Mark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Set;

public class JDBCMarkDao extends JDBCDao implements MarkDao {

    private static final Logger log = LoggerFactory.getLogger(JDBCMarkDao.class);

    public JDBCMarkDao(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Mark entity) {

    }

    @Override
    public Mark findById(Integer id) {
        return null;
    }

    @Override
    public Set<Mark> findAll() {
        return null;
    }

    @Override
    public void update(Mark entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }

}
