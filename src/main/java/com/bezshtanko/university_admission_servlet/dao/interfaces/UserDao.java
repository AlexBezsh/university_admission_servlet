package com.bezshtanko.university_admission_servlet.dao.interfaces;

import com.bezshtanko.university_admission_servlet.model.user.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findByEmail(String email);
}
