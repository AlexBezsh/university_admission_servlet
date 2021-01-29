package com.bezshtanko.university_admission_servlet.dao.mapper;

import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentMapper extends AbstractMapper<Enrollment> {

    private static final Logger log = LoggerFactory.getLogger(EnrollmentMapper.class);

    @Override
    public Enrollment get(ResultSet resultSet) throws SQLException {
        return null;
    }

}
