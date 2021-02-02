package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.dao.interfaces.EnrollmentDao;
import com.bezshtanko.university_admission_servlet.dao.interfaces.FacultyDao;
import com.bezshtanko.university_admission_servlet.exception.FacultyNotExistsException;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FacultyService extends Service {

    private static final Logger log = LoggerFactory.getLogger(FacultyService.class);

    public Faculty findById(Long id) {
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            return facultyDao.findById(id).orElseThrow(FacultyNotExistsException::new);
        }
    }

    public List<Faculty> findAll() {
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            return facultyDao.findAll();
        }
    }

    public Faculty getFacultyWithEnrollments(Long id) {
        log.info("Getting faculty with id: '{}' with enrollments", id);
        try (FacultyDao facultyDao = daoFactory.createFacultyDao();
             EnrollmentDao enrollmentDao = daoFactory.createEnrollmentDao()) {
            Faculty faculty = facultyDao.findById(id).orElseThrow(FacultyNotExistsException::new);
            List<Enrollment> enrollments = enrollmentDao.findAllByFacultyId(id);
            faculty.setEnrollments(enrollments);
            return faculty;
        }
    }

}
