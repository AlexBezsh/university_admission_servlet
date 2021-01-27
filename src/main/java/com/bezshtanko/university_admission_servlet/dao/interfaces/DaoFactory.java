package com.bezshtanko.university_admission_servlet.dao.interfaces;

public abstract class DaoFactory {

    public abstract EnrollmentDao createEnrollmentDao();
    public abstract FacultyDao createFacultyDao();
    public abstract MarkDao createMarkDao();
    public abstract SubjectDao createSubjectDao();
    public abstract UserDao createUserDao();

}
