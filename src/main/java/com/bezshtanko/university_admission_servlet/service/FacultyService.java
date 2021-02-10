package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.dao.interfaces.DaoFactory;
import com.bezshtanko.university_admission_servlet.dao.interfaces.EnrollmentDao;
import com.bezshtanko.university_admission_servlet.dao.interfaces.FacultyDao;
import com.bezshtanko.university_admission_servlet.dto.PageInfoDTO;
import com.bezshtanko.university_admission_servlet.exception.FacultyNotExistException;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FacultyService extends Service {

    private static final Logger log = LoggerFactory.getLogger(FacultyService.class);

    private static final Comparator<Enrollment> ACTIVE_FACULTY_ENROLLMENTS_COMPARATOR = Comparator
            .comparing((Enrollment e) -> e.getUser().getStatus())
            .thenComparing(Enrollment::getStatus)
            .thenComparing(Comparator.comparing(Enrollment::getMarksSum).reversed());

    private static final Comparator<Enrollment> CLOSED_FACULTY_ENROLLMENTS_COMPARATOR = Comparator
            .comparing((Enrollment e) -> e.getUser().getStatus()).reversed()
            .thenComparing(Comparator.comparing(Enrollment::getMarksSum).reversed());

    public FacultyService(DaoFactory daoFactory) {
        super(daoFactory);
    }

    public void save(Faculty faculty) {
        log.info("Saving new faculty {}", faculty);
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            facultyDao.save(faculty);
        }
    }

    public Faculty findById(Long id) {
        log.info("Getting faculty with id '{}'", id);
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            return facultyDao.findById(id).orElseThrow(FacultyNotExistException::new);
        }
    }

    public List<Faculty> findAll(PageInfoDTO pageInfo) {
        log.info("Getting all faculties");
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            return facultyDao.findAll(pageInfo);
        }
    }

    public Faculty findWithEnrollments(Long id) {
        log.info("Getting faculty with id: '{}' with enrollments", id);
        try (FacultyDao facultyDao = daoFactory.createFacultyDao();
             EnrollmentDao enrollmentDao = daoFactory.createEnrollmentDao()) {
            Faculty faculty = facultyDao.findById(id).orElseThrow(FacultyNotExistException::new);
            return faculty.isActive()
                    ? setEnrollments(faculty, enrollmentDao.findAllRelevantByFacultyId(id))
                    : setEnrollments(faculty, enrollmentDao.findAllFinalizedByFacultyId(id));
        }
    }

    public void update(Faculty faculty) {
        log.info("Updating faculty with id '{}'", faculty.getId());
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            facultyDao.update(faculty);
        }
    }

    public void finalizeFaculty(Long id) {
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            log.info("Finalization of faculty with id '{}' started", id);
            facultyDao.finalizeFaculty(id);
            log.info("Finalization finished successfully");
        }
    }

    public void deleteById(Long id) {
        log.info("Deleting faculty with id '{}'", id);
        try (FacultyDao facultyDao = daoFactory.createFacultyDao()) {
            facultyDao.deleteById(id);
            log.info("Faculty with id '{}' was successfully deleted", id);
        }
    }

    private Faculty setEnrollments(Faculty faculty, List<Enrollment> enrollments) {
        faculty.setEnrollments(enrollments
                .stream()
                .peek(e -> e.setFaculty(faculty))
                .sorted(faculty.isActive() ? ACTIVE_FACULTY_ENROLLMENTS_COMPARATOR : CLOSED_FACULTY_ENROLLMENTS_COMPARATOR)
                .collect(Collectors.toList()));
        return faculty;
    }

}
