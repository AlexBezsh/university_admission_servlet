package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.dao.interfaces.EnrollmentDao;
import com.bezshtanko.university_admission_servlet.dao.interfaces.MarkDao;
import com.bezshtanko.university_admission_servlet.dao.interfaces.UserDao;
import com.bezshtanko.university_admission_servlet.exception.AuthenticationException;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class EnrollmentService extends Service {

    private static final Logger log = LoggerFactory.getLogger(EnrollmentService.class);

    public void save(Enrollment enrollment) {
        try (EnrollmentDao enrollmentDao = daoFactory.createEnrollmentDao()) {
            log.info("saving new enrollment with faculty id '{}' and user id '{}'", enrollment.getFaculty().getId(), enrollment.getUser().getId());
            enrollmentDao.save(enrollment);
        }
    }

    public List<Enrollment> findAllByUserId(Long id) {
        try (EnrollmentDao enrollmentDao = daoFactory.createEnrollmentDao()) {
            log.info("getting enrollments for user with id '{}' from database", id);
            return enrollmentDao.findAllByUserId(id);
        }
    }

}
