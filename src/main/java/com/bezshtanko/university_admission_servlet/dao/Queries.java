package com.bezshtanko.university_admission_servlet.dao;

public class Queries {

    public static final String SELECT_USER_BY_EMAIL =
            "SELECT " + DatabaseColumns.USER_ID.value() + " as " + Aliases.USER_ID.value() + ", "
                    + DatabaseColumns.USER_FULL_NAME.value() + ", "
                    + DatabaseColumns.USER_EMAIL.value() + ", "
                    + DatabaseColumns.USER_PASSWORD.value() + ", "
                    + DatabaseColumns.USER_STATUS.value() + " as " + Aliases.USER_STATUS.value() + ", "
                    + DatabaseColumns.USER_CITY.value() + ", "
                    + DatabaseColumns.USER_REGION.value() + ", "
                    + DatabaseColumns.USER_EDUCATION.value() +
                    " FROM user WHERE email = ?";

}
