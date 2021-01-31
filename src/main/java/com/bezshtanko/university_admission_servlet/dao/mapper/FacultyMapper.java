package com.bezshtanko.university_admission_servlet.dao.mapper;

import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.faculty.FacultyStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyMapper extends AbstractMapper<Faculty> {

    private static final Logger log = LoggerFactory.getLogger(FacultyMapper.class);

    @Override
    public Faculty get(ResultSet resultSet) throws SQLException {
        log.info("faculty mapping started");
        return Faculty.builder()
                .setId(resultSet.getLong("f_id"))
                .setNameEn(resultSet.getString("f_name_en"))
                .setNameUa(resultSet.getString("f_name_ua"))
                .setStatus(FacultyStatus.valueOf(resultSet.getString("f_status")))
                .setDescriptionEn(resultSet.getString("description_en"))
                .setDescriptionUa(resultSet.getString("description_ua"))
                .setStateFundedPlaces(resultSet.getInt("state_funded_places"))
                .setContractPlaces(resultSet.getInt("contract_places"))
                .build();
    }

}
