package com.bezshtanko.university_admission_servlet.dao.mapper;

import com.bezshtanko.university_admission_servlet.dao.Aliases;
import com.bezshtanko.university_admission_servlet.dao.DatabaseColumns;
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
        return User.builder()
                .setId(resultSet.getLong(Aliases.USER_ID.value()))
                .setFullName(resultSet.getString(DatabaseColumns.USER_FULL_NAME.value()))
                .setEmail(resultSet.getString(DatabaseColumns.USER_EMAIL.value()))
                .setPassword(resultSet.getString(DatabaseColumns.USER_PASSWORD.value()))
                .setStatus(UserStatus.valueOf(resultSet.getString(Aliases.USER_STATUS.value())))
                .setRoles(new HashSet<>(Collections.singletonList(UserRole.valueOf(resultSet.getString(Aliases.USER_ROLE_ROLES.value())))))
                .setCity(resultSet.getString(DatabaseColumns.USER_CITY.value()))
                .setRegion(resultSet.getString(DatabaseColumns.USER_REGION.value()))
                .setEducation(resultSet.getString(DatabaseColumns.USER_EDUCATION.value()))
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
