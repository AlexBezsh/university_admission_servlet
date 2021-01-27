package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.dao.interfaces.DaoFactory;
import com.bezshtanko.university_admission_servlet.dao.jdbc_impl.JDBCDaoFactory;

public class Service {

    protected final DaoFactory daoFactory = JDBCDaoFactory.getInstance();

}
