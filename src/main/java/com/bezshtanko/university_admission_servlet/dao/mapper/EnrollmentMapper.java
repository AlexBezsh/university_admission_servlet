package com.bezshtanko.university_admission_servlet.dao.mapper;

import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.enrollment.EnrollmentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentMapper extends AbstractMapper<Enrollment> {

    private static final Logger log = LoggerFactory.getLogger(EnrollmentMapper.class);

    @Override
    public Enrollment get(ResultSet resultSet) throws SQLException {
        log.info("faculty mapping started");
        return Enrollment.builder()
                .setId(resultSet.getLong("e_id"))
                .setStatus(EnrollmentStatus.valueOf(resultSet.getString("e_status")))
                .build();
    }

}
