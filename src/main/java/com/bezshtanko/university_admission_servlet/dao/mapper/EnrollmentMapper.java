package com.bezshtanko.university_admission_servlet.dao.mapper;

import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentMapper extends AbstractMapper<Enrollment> {

    //todo все маперы содержат только методы и никакого состояния - сделать фабрику, хранящую уже созданные объекты, чтобы не создавать каждый раз

    @Override
    public Enrollment get(ResultSet resultSet) throws SQLException {
        return null;
    }

}
