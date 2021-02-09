package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.dao.interfaces.DaoFactory;

public abstract class Service {

    protected final DaoFactory daoFactory;

    public Service(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
