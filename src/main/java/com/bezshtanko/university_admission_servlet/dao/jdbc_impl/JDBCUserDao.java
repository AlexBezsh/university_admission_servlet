package com.bezshtanko.university_admission_servlet.dao.jdbc_impl;

import com.bezshtanko.university_admission_servlet.dao.interfaces.UserDao;
import com.bezshtanko.university_admission_servlet.dao.mapper.UserMapper;
import com.bezshtanko.university_admission_servlet.model.user.User;
import com.bezshtanko.university_admission_servlet.model.user.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
            log.info("Transaction has been opened");
            saveUserStmt.execute();
            saveRolesStmt.execute();
            connection.commit();
            connection.setAutoCommit(true);
            log.info("Queries successfully executed for user: {}", user);
        } catch (SQLException e) {
            log.error("Exception occurred during saving new user: {}", user);
            handleConnectionAfterException(connection);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        String query = "SELECT id as u_id, full_name, email, password, status as u_status, city, region, education, roles FROM user " +
                "JOIN user_role on user.id = user_role.user_id WHERE id = ?";

        Optional<User> result;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            log.info("Prepared statement created for user with id '{}'", id);
            result = Optional.of(executeFindUsersQuery(ps).get(0));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "SELECT id as u_id, full_name, email, password, status as u_status, city, region, education, roles FROM user " +
                "JOIN user_role on user.id = user_role.user_id WHERE email = ?";

        Optional<User> result;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            log.info("Prepared statement created for user with email {}", email);
            result = Optional.of(executeFindUsersQuery(ps).get(0));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean blockUser(Long id) {
        return updateStatus(id, UserStatus.BLOCKED);
    }

    @Override
    public boolean unblockUser(Long id) {
        return updateStatus(id, UserStatus.ACTIVE);
    }

    @Override
    public boolean setEnrolledContract(Long id) {
        return updateStatus(id, UserStatus.ENROLLED_CONTRACT);
    }

    @Override
    public boolean setEnrolledStateFunded(Long id) {
        return updateStatus(id, UserStatus.ENROLLED_STATE_FUNDED);
    }

    private boolean updateStatus(Long userId, UserStatus status) {
        log.info("Setting status '{}' to user with id '{}'", status, userId);
        String query = "UPDATE user SET status = '" + status + "' WHERE id = ?";
        return updateEntityStatus(userId, query);
    }

    private List<User> executeFindUsersQuery(PreparedStatement ps) throws SQLException {
        ResultSet resultSet = ps.executeQuery();
        log.info("Query successfully executed");

        UserMapper mapper = new UserMapper();
        Map<Long, User> users = new HashMap<>();
        User user;
        while (resultSet.next()) {
            user = mapper.get(resultSet);
            mapper.makeUnique(users, user);
        }
        return new ArrayList<>(users.values());
    }

}
