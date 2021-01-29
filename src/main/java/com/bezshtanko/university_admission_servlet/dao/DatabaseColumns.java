package com.bezshtanko.university_admission_servlet.dao;

public enum DatabaseColumns {

    USER_ID("id"),
    USER_FULL_NAME("full_name"),
    USER_EMAIL("email"),
    USER_PASSWORD("password"),
    USER_STATUS("status"),
    USER_CITY("city"),
    USER_REGION("region"),
    USER_EDUCATION("education"),

    USER_ROLE_USER_ID("user_id"),
    USER_ROLE_ROLES("roles"),

    FACULTY_ID("id"),
    FACULTY_NAME_EN("name_en"),
    FACULTY_NAME_UA("name_ua"),
    FACULTY_STATUS("status"),
    FACULTY_DESCRIPTION_EN("description_en"),
    FACULTY_DESCRIPTION_UA("description_ua"),
    FACULTY_STATE_FUNDED_PLACES("state_funded_places"),
    FACULTY_CONTRACT_PLACES("contract_places"),

    FACULTY_SUBJECTS_FACULTY_ID("faculty_id"),
    FACULTY_SUBJECTS_SUBJECTS_ID("subjects_id"),

    ENROLLMENT_ID("id"),
    ENROLLMENT_USER_ID("user_id"),
    ENROLLMENT_FACULTY_ID("faculty_id"),
    ENROLLMENT_STATUS("status"),

    MARKS_ID("id"),
    MARKS_ENROLLMENT_ID("enrollment_id"),
    MARKS_SUBJECT_ID("subject_id"),
    MARKS_MARK("mark"),

    SUBJECT_ID("id"),
    SUBJECT_TYPE("type"),
    SUBJECT_NAME_EN("name_en"),
    SUBJECT_NAME_UA("name_ua");

    private final String column;

    DatabaseColumns(String column) {
        this.column = column;
    }

    public String value() {
        return column;
    }


}
