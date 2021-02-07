package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.dao.interfaces.EnrollmentDao;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.enrollment.EnrollmentStatus;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.mark.Mark;
import com.bezshtanko.university_admission_servlet.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EnrollmentService extends Service {

    private static final Logger log = LoggerFactory.getLogger(EnrollmentService.class);

    public void save(UserDTO user, Faculty faculty, List<Mark> marks) {
        Enrollment enrollment = Enrollment.builder()
                .setFaculty(faculty)
                .setStatus(EnrollmentStatus.NEW)
                .setUser(User.builder()
                        .setId(user.getId())
                        .build())
                .setMarks(marks)
                .build();
        marks.forEach(m -> m.setEnrollment(enrollment));
        user.getEnrollments().add(enrollment);
        faculty.getEnrollments().add(enrollment);

        try (EnrollmentDao enrollmentDao = daoFactory.createEnrollmentDao()) {
            log.info("Saving new enrollment with faculty id '{}' and user id '{}'", faculty.getId(), user.getId());
            enrollmentDao.save(enrollment);
        }
    }

    public void setApproved(Long id) {
        try (EnrollmentDao enrollmentDao = daoFactory.createEnrollmentDao()) {
            log.info("Setting  status 'APPROVED' in enrollment with id '{}'", id);
            enrollmentDao.setApproved(id);
        }
    }

    public List<Enrollment> findAllByUserId(Long id) {
        try (EnrollmentDao enrollmentDao = daoFactory.createEnrollmentDao()) {
            log.info("Getting enrollments for user with id '{}' from database", id);
            return enrollmentDao.findAllByUserId(id);
        }
    }

}
