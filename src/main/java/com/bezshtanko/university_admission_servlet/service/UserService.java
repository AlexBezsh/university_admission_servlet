package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.dao.interfaces.UserDao;
import com.bezshtanko.university_admission_servlet.exception.AuthenticationException;
import com.bezshtanko.university_admission_servlet.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class UserService extends Service {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public User login(String email, String password) {
        log.info("creating user dao");
        try (UserDao userDao = daoFactory.createUserDao()) {
            log.info("getting user with email {} from database", email);
            Optional<User> user = userDao.findByEmail(email);
            if (!user.isPresent() || !encodePassword(password).matches(user.get().getPassword())) {
                throw new AuthenticationException();
            }
            return user.get();
        }
    }

    public static String encodePassword(String password) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] bytes = messageDigest.digest();

            StringBuilder stringBuilder = new StringBuilder();
            for (byte oneByte : bytes) {
                stringBuilder.append(Character.forDigit((oneByte >> 4) & 0xf, 16))
                        .append(Character.forDigit((oneByte & 0xf), 16));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
