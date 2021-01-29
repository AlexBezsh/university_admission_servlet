package com.bezshtanko.university_admission_servlet.dao.mapper;

import com.bezshtanko.university_admission_servlet.model.user.User;
import com.bezshtanko.university_admission_servlet.model.user.UserRole;
import com.bezshtanko.university_admission_servlet.model.user.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

public class UserMapper extends AbstractMapper<User> {

    private static final Logger log = LoggerFactory.getLogger(UserMapper.class);

    @Override
    public User get(ResultSet resultSet) throws SQLException {
        log.info("user mapping started");
        return User.builder()
                .setId(resultSet.getLong("u_id"))
                .setFullName(resultSet.getString("full_name"))
                .setEmail(resultSet.getString("email"))
                .setPassword(resultSet.getString("password"))
                .setStatus(UserStatus.valueOf(resultSet.getString("u_status")))
                .setRoles(new HashSet<>(Collections.singletonList(UserRole.valueOf(resultSet.getString("roles")))))
                .setCity(resultSet.getString("city"))
                .setRegion(resultSet.getString("region"))
                .setEducation(resultSet.getString("education"))
                .build();
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        User cachedUser = cache.get(user.getId());
        cachedUser.getRoles().addAll(user.getRoles());
        return cachedUser;
    }

}
