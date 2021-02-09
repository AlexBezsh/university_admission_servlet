package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.dao.interfaces.DaoFactory;
import com.bezshtanko.university_admission_servlet.dao.interfaces.EnrollmentDao;
import com.bezshtanko.university_admission_servlet.dao.interfaces.UserDao;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.exception.AuthenticationException;
import com.bezshtanko.university_admission_servlet.exception.UserNotExistException;
import com.bezshtanko.university_admission_servlet.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class UserService extends Service {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(DaoFactory daoFactory) {
        super(daoFactory);
    }

    public UserDTO login(String email, String password) {
        log.info("Creating user dao");
        try (UserDao userDao = daoFactory.createUserDao();
             EnrollmentDao enrollmentDao = daoFactory.createEnrollmentDao()) {
            log.info("Getting user with email {} from database", email);
            Optional<User> user = userDao.findByEmail(email);
            if (!user.isPresent() || !encodePassword(password).matches(user.get().getPassword())) {
                throw new AuthenticationException();
            }
            User userFromDB = user.get();
            userFromDB.setEnrollments(enrollmentDao.findAllByUserId(userFromDB.getId()));
            return new UserDTO(userFromDB);
        }
    }

    public UserDTO getByEmail(String email) {
        log.info("Getting user with email '{}'", email);
        try (UserDao userDao = daoFactory.createUserDao()) {
            return new UserDTO(userDao.findByEmail(email).orElseThrow(UserNotExistException::new));
        }
    }

    public void save(User user) {
        log.info("Saving new user: {}", user);
        user.setPassword(encodePassword(user.getPassword()));
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.save(user);
        }
    }

    public User getWithEnrollments(Long userId) {
        log.info("Getting user with id '{}' with enrollments", userId);
        try (UserDao userDao = daoFactory.createUserDao();
             EnrollmentDao enrollmentDao = daoFactory.createEnrollmentDao()) {
            User user = userDao.findById(userId).orElseThrow(UserNotExistException::new);
            user.setPassword(null);
            user.setEnrollments(enrollmentDao.findAllByUserId(userId));
            user.getEnrollments().forEach(e -> e.setUser(user));
            return user;
        }
    }

    public void blockUser(Long userId) {
        log.info("Blocking user with id '{}'", userId);
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.blockUser(userId);
        }
    }

    public void unblockUser(Long userId) {
        log.info("Unblocking user with id '{}'", userId);
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.unblockUser(userId);
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
