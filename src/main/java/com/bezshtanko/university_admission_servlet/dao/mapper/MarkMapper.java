package com.bezshtanko.university_admission_servlet.dao.mapper;

import com.bezshtanko.university_admission_servlet.model.mark.Mark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarkMapper extends AbstractMapper<Mark> {

    @Override
    public Mark get(ResultSet resultSet) throws SQLException {
        return Mark.builder()
                .setId(resultSet.getLong("m_id"))
                .setMark(new BigDecimal(resultSet.getString("mark")))
                .build();
    }

}
