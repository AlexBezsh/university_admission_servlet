package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

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
    public void save(User user) {
        String saveUserQuery = "INSERT INTO user(full_name, email, password, status, city, region, education) VALUES(?, ?, ?, ?, ?, ?, ?)";
        StringBuilder saveRolesQuery = new StringBuilder("INSERT INTO user_role(user_id, roles) VALUES");
        user.getRoles().forEach(r -> saveRolesQuery.append("((select id from user where email = '").append(user.getEmail()).append("'), '").append(r.toString()).append("'), "));
        saveRolesQuery.setLength(saveRolesQuery.length() - 2);

        try (PreparedStatement saveUserStmt = connection.prepareStatement(saveUserQuery);
             PreparedStatement saveRolesStmt = connection.prepareStatement(saveRolesQuery.toString())) {
            saveUserStmt.setString(1, user.getFullName());
            saveUserStmt.setString(2, user.getEmail());
            saveUserStmt.setString(3, user.getPassword());
            saveUserStmt.setString(4, user.getStatus().toString());
            saveUserStmt.setString(5, user.getCity());
            saveUserStmt.setString(6, user.getRegion());
            saveUserStmt.setString(7, user.getEducation());
            log.info("prepared statements created for new user: {}", user);

            connection.setAutoCommit(false);
            saveUserStmt.execute();
            saveRolesStmt.execute();
            connection.commit();
            log.info("queries successfully executed for user: {}", user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        String query = "SELECT id as u_id, full_name, email, password, status as u_status, city, region, education, roles FROM user " +
                "JOIN user_role on user.id = user_role.user_id WHERE email = ?";

        Optional<User> result = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            log.info("prepared statement created for user with email {}", email);

            ResultSet resultSet = ps.executeQuery();
            log.info("query executed for user with email {}", email);

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
