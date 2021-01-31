package com.bezshtanko.university_admission_servlet.dao.mapper;

import com.bezshtanko.university_admission_servlet.model.subject.Subject;
import com.bezshtanko.university_admission_servlet.model.subject.SubjectType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper extends AbstractMapper<Subject> {

    private static final Logger log = LoggerFactory.getLogger(SubjectMapper.class);

    @Override
    public Subject get(ResultSet resultSet) throws SQLException {
        log.info("subject mapping started");
        return Subject.builder()
                .setId(resultSet.getLong("s_id"))
                .setNameEn(resultSet.getString("s_name_en"))
                .setNameUa(resultSet.getString("s_name_ua"))
                .setType(SubjectType.valueOf(resultSet.getString("s_type")))
                .build();
    }

}
