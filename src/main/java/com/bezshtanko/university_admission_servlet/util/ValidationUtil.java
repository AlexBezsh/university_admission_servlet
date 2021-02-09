package com.bezshtanko.university_admission_servlet.util;

import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.user.User;

public class ValidationUtil {

    public static String getUserDataErrors(User user) {
        StringBuilder errors = new StringBuilder();

        String fullName = user.getFullName();
        if (fullName == null || fullName.length() < 5 || fullName.length() > 120) {
            errors.append("fullNameError&");
        }

        String email = user.getEmail();
        if (email == null || !email.matches(".+@.+") || email.length() > 320) {
            errors.append("emailError&");
        }

        String password = user.getPassword();
        if (password == null || !password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+).{6,20}")) {
            errors.append("passwordError&");
        }

        String city = user.getCity();
        if (city == null || city.length() < 2 || city.length() > 100) {
            errors.append("cityError&");
        }

        String region = user.getRegion();
        if (region == null || region.length() < 2 || region.length() > 100) {
            errors.append("regionError&");
        }

        String education = user.getEducation();
        if (education == null || education.length() < 2 || education.length() > 100) {
            errors.append("educationError&");
        }

        String result = errors.toString();
        return result.endsWith("&") ? result.substring(0, result.length() - 1) : result;
    }

    public static String getFacultyDataErrors(Faculty faculty) {
        StringBuilder errors = new StringBuilder();

        String nameEn = faculty.getNameEn();
        if (nameEn == null || nameEn.length() < 2 || nameEn.length() > 250) {
            errors.append("nameEnError&");
        }

        String nameUa = faculty.getNameUa();
        if (nameUa == null || nameUa.length() < 2 || nameUa.length() > 250) {
            errors.append("nameUaError&");
        }

        String descriptionEn = faculty.getDescriptionEn();
        if (descriptionEn == null || descriptionEn.length() < 10) {
            errors.append("descriptionEnError&");
        }

        String descriptionUa = faculty.getDescriptionUa();
        if (descriptionUa == null || descriptionUa.length() < 10) {
            errors.append("descriptionUaError&");
        }

        Integer stateFundedPlaces = faculty.getStateFundedPlaces();
        if (stateFundedPlaces == null || stateFundedPlaces < 0) {
            errors.append("stateFundedPlacesError&");
        }

        Integer contractPlaces = faculty.getContractPlaces();
        if (contractPlaces == null || contractPlaces < 0) {
            errors.append("contractPlacesError&");
        }

        String result = errors.toString();
        return result.endsWith("&") ? result.substring(0, result.length() - 1) : result;
    }

}
