package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.Queries;
import com.bezshtanko.university_admission_servlet.dao.interfaces.UserDao;
import com.bezshtanko.university_admission_servlet.dao.mapper.UserMapper;
import com.bezshtanko.university_admission_servlet.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class JDBCUserDao extends JDBCDao implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(JDBCUserDao.class);

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

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> result = Optional.empty();
        try(PreparedStatement ps = connection.prepareStatement(Queries.SELECT_USER_BY_EMAIL)) {
            ps.setString(1, email);

            System.out.println("query is about to be executed");

            ResultSet resultSet = ps.executeQuery();

            System.out.println("query is executed");

            UserMapper mapper = new UserMapper();
            if (resultSet.next()) {
                result = Optional.of(mapper.get(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
