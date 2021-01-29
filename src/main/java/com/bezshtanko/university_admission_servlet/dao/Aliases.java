package com.bezshtanko.university_admission_servlet.dao;

public enum Aliases {

    USER_ID("u_id"),
    USER_STATUS("u_status"),
    USER_ROLE_USER_ID("role_u_id"),
    USER_ROLE_ROLES("u_role"),

    FACULTY_ID("f_id"),
    FACULTY_NAME_EN("f_name_en"),
    FACULTY_NAME_UA("f_name_ua"),
    FACULTY_STATUS("f_status"),

    ENROLLMENT_ID("e_id"),
    ENROLLMENT_USER_ID("e_u_id"),
    ENROLLMENT_FACULTY_ID("e_f_id"),
    ENROLLMENT_STATUS("e_status"),

    MARKS_ID("m_id"),
    MARKS_ENROLLMENT_ID("m_e_id"),
    MARKS_SUBJECT_ID("m_s_id"),

    SUBJECT_ID("s_id"),
    SUBJECT_TYPE("s_type"),
    SUBJECT_NAME_EN("s_name_en"),
    SUBJECT_NAME_UA("s_name_ua");

    private final String columnAlias;

    Aliases(String columnAlias) {
        this.columnAlias = columnAlias;
    }

    public String value() {
        return columnAlias;
    }

}
