package com.bezshtanko.university_admission_servlet.dao.mapper;

import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyMapper extends AbstractMapper<Faculty> {

    private static final Logger log = LoggerFactory.getLogger(FacultyMapper.class);

    @Override
    public Faculty get(ResultSet resultSet) throws SQLException {
        return null;
    }

}
