package com.bezshtanko.university_admission_servlet.util;

import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.user.User;

public class ValidationUtil {

    public static String getUserErrors(User user) {
        String errors = "";

        String fullName = user.getFullName();
        if (fullName == null || fullName.length() < 5 || fullName.length() > 120) {
            errors += "fullNameError&";
        }

        if (!user.getEmail().matches(".+@.+")) {
            errors += "emailError&";
        }

        if (!user.getPassword().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+).{6,}")) {
            errors += "passwordError&";
        }

        String city = user.getCity();
        if (city == null || city.length() < 2 || city.length() > 100) {
            errors += "cityError&";
        }

        String region = user.getRegion();
        if (region == null || region.length() < 2 || region.length() > 100) {
            errors += "regionError&";
        }

        String education = user.getEducation();
        if (education == null || education.length() < 2 || education.length() > 100) {
            errors += "educationError&";
        }

        return errors;
    }

    public static String getFacultyErrors(Faculty faculty) {
        String errors = "";

        String nameEn = faculty.getNameEn();
        if (nameEn == null || nameEn.length() < 2 || nameEn.length() > 250) {
            errors += "nameEnError&";
        }

        String nameUa = faculty.getNameUa();
        if (nameUa == null || nameUa.length() < 2 || nameUa.length() > 250) {
            errors += "nameUaError&";
        }

        String descriptionEn = faculty.getDescriptionEn();
        if (descriptionEn == null || descriptionEn.length() < 10) {
            errors += "descriptionEnError&";
        }

        String descriptionUa = faculty.getDescriptionUa();
        if (descriptionUa == null || descriptionUa.length() < 10) {
            errors += "descriptionUaError&";
        }

        if (faculty.getStateFundedPlaces() < 0) {
            errors += "stateFundedPlacesError&";
        }

        if (faculty.getContractPlaces() < 0) {
            errors += "contractPlacesError&";
        }

        return errors;
    }

}
