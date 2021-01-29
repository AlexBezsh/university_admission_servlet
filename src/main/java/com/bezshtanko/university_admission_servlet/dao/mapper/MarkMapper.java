package com.bezshtanko.university_admission_servlet.dao.mapper;

import com.bezshtanko.university_admission_servlet.model.mark.Mark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarkMapper extends AbstractMapper<Mark> {

    private static final Logger log = LoggerFactory.getLogger(MarkMapper.class);

    @Override
    public Mark get(ResultSet resultSet) throws SQLException {
        return null;
    }

}
