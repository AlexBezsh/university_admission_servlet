package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.dao.DataSourceHolder;
import com.bezshtanko.university_admission_servlet.dao.interfaces.DaoFactory;

public enum Services {
    USER_SERVICE(new UserService(DaoFactory.getInstance(DataSourceHolder.getDataSource()))),
    FACULTY_SERVICE(new FacultyService(DaoFactory.getInstance(DataSourceHolder.getDataSource()))),
    SUBJECT_SERVICE(new SubjectService(DaoFactory.getInstance(DataSourceHolder.getDataSource()))),
    ENROLLMENT_SERVICE(new EnrollmentService(DaoFactory.getInstance(DataSourceHolder.getDataSource())));

    private final Service service;

    Services(Service service) {
        this.service = service;
    }

    public Service get() {
        return service;
    }
}
