package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.interfaces.FacultyDao;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Set;

public class JDBCFacultyDao extends JDBCDao implements FacultyDao {

    private static final Logger log = LoggerFactory.getLogger(JDBCFacultyDao.class);

    public JDBCFacultyDao(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Faculty entity) {

    }

    @Override
    public Faculty findById(Integer id) {
        return null;
    }

    @Override
    public Set<Faculty> findAll() {
        return null;
    }

    @Override
    public void update(Faculty entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }

}
