package com.bezshtanko.university_admission_servlet.dao;

public class Queries {

    public static final String SELECT_USER_BY_EMAIL =
            "SELECT id as u_id, full_name, email, password, status as u_status, city, region, education, roles FROM user " +
                    "JOIN user_role on user.id = user_role.user_id WHERE email = ?";



}
