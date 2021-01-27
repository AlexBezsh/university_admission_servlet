package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.interfaces.UserDao;
import com.bezshtanko.university_admission_servlet.model.user.User;

import java.sql.Connection;
import java.util.Set;

public class JDBCUserDao extends JDBCDao implements UserDao {

    public JDBCUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public Set<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }

}
