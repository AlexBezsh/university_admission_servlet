package com.bezshtanko.university_admission_servlet.dao.mapper;

import com.bezshtanko.university_admission_servlet.model.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper extends AbstractMapper<Subject> {

    private static final Logger log = LoggerFactory.getLogger(SubjectMapper.class);

    @Override
    public Subject get(ResultSet resultSet) throws SQLException {
        return null;
    }

}
