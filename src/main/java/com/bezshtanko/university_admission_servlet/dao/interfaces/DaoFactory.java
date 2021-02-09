package com.bezshtanko.university_admission_servlet.dao.interfaces;

import com.bezshtanko.university_admission_servlet.dao.jdbc_impl.JDBCDaoFactory;

import javax.sql.DataSource;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract EnrollmentDao createEnrollmentDao();
    public abstract FacultyDao createFacultyDao();
    public abstract MarkDao createMarkDao();
    public abstract SubjectDao createSubjectDao();
    public abstract UserDao createUserDao();

    public static DaoFactory getInstance(DataSource dataSource){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory(dataSource);
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }

}
