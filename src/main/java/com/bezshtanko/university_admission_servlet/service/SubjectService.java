package com.bezshtanko.university_admission_servlet.service;

import com.bezshtanko.university_admission_servlet.dao.interfaces.DaoFactory;
import com.bezshtanko.university_admission_servlet.dao.interfaces.SubjectDao;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SubjectService extends Service {

    private static final Logger log = LoggerFactory.getLogger(SubjectService.class);

    public SubjectService(DaoFactory daoFactory) {
        super(daoFactory);
    }

    public List<Subject> findAll() {
        log.info("Getting all subjects");
        try (SubjectDao subjectDao = daoFactory.createSubjectDao()) {
            return subjectDao.findAll();
        }
    }

}