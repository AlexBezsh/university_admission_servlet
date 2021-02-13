package com.bezshtanko.university_admission_servlet.dao.mapper;

import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.faculty.FacultyStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyMapper extends AbstractMapper<Faculty> {

    @Override
    public Faculty get(ResultSet resultSet) throws SQLException {
        return Faculty.builder()
                .setId(resultSet.getLong("f_id"))
                .setNameEn(resultSet.getString("f_name_en"))
                .setNameUa(resultSet.getString("f_name_ua"))
                .setStatus(FacultyStatus.valueOf(resultSet.getString("f_status")))
                .setDescriptionEn(resultSet.getString("f_description_en"))
                .setDescriptionUa(resultSet.getString("f_description_ua"))
                .setStateFundedPlaces(resultSet.getInt("f_state_funded_places"))
                .setContractPlaces(resultSet.getInt("f_contract_places"))
                .build();
    }

}
