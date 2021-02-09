package com.bezshtanko.university_admission_servlet;

import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.user.User;
import com.bezshtanko.university_admission_servlet.util.ValidationUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidationUtilTest {

    private static final int CHARACTERS_IN_TEST_STRING = 350;
    private static final String TEST_STRING = Stream.generate(() -> "s")
            .limit(CHARACTERS_IN_TEST_STRING)
            .collect(Collectors.joining());

    @Test
    public void ShouldReturnEmptyStringWhenUserIsValid() {
        Assert.assertTrue(ValidationUtil.getUserDataErrors(TestData.TEST_USER).isEmpty());
    }

    @Test
    public void ShouldReturnStringWithErrorsWhenUserHasNullValuesInVariables() {
        String expected = "fullNameError&emailError&passwordError&cityError&regionError&educationError";
        User user = User.builder()
                .setFullName(null)
                .setEmail(null)
                .setPassword(null)
                .setCity(null)
                .setRegion(null)
                .setEducation(null)
                .build();

        Assert.assertEquals(expected, ValidationUtil.getUserDataErrors(user));
    }

    @Test
    public void ShouldReturnStringWithErrorsWhenUserHasTooShortValuesInVariables() {
        String expected = "fullNameError&emailError&passwordError&cityError&regionError&educationError";
        User user = User.builder()
                .setFullName("Name")
                .setEmail("e@")
                .setPassword("Str1")
                .setCity("C")
                .setRegion("R")
                .setEducation("E")
                .build();

        Assert.assertEquals(expected, ValidationUtil.getUserDataErrors(user));
    }

    @Test
    public void ShouldReturnEmptyStringWhenUserHasLongEnoughValuesInVariables() {
        User user = User.builder()
                .setFullName("Maria")
                .setEmail("e@e")
                .setPassword("Stron1")
                .setCity("AA")
                .setRegion("AA")
                .setEducation("AA")
                .build();

        Assert.assertTrue(ValidationUtil.getUserDataErrors(user).isEmpty());
    }

    @Test
    public void ShouldReturnEmptyStringWhenUserHasValuesWithMaxLengthInVariables() {
        User user = User.builder()
                .setFullName(TEST_STRING.substring(0, 120))
                .setEmail(TEST_STRING.substring(0, 19) + "@" + TEST_STRING.substring(0, 300))
                .setPassword("S" + TEST_STRING.substring(0, 18) + "1")
                .setCity(TEST_STRING.substring(0, 100))
                .setRegion(TEST_STRING.substring(0, 100))
                .setEducation(TEST_STRING.substring(0, 100))
                .build();

        Assert.assertTrue(ValidationUtil.getUserDataErrors(user).isEmpty());
    }

    @Test
    public void ShouldReturnStringWithErrorsWhenUserHasTooLongValuesInVariables() {
        String expected = "fullNameError&emailError&passwordError&cityError&regionError&educationError";
        User user = User.builder()
                .setFullName(TEST_STRING.substring(0, 121))
                .setEmail(TEST_STRING.substring(0, 20) + "@" + TEST_STRING.substring(0, 300))
                .setPassword("S" + TEST_STRING.substring(0, 19) + "1")
                .setCity(TEST_STRING.substring(0, 101))
                .setRegion(TEST_STRING.substring(0, 101))
                .setEducation(TEST_STRING.substring(0, 101))
                .build();

        Assert.assertEquals(expected, ValidationUtil.getUserDataErrors(user));
    }

    @Test
    public void ShouldReturnEmptyStringWhenFacultyIsValid() {
        Assert.assertTrue(ValidationUtil.getFacultyDataErrors(TestData.TEST_FACULTY).isEmpty());
    }

    @Test
    public void ShouldReturnStringWithErrorsWhenFacultyHasNullValuesInVariables() {
        String expected = "nameEnError&nameUaError&descriptionEnError&descriptionUaError&stateFundedPlacesError&contractPlacesError";
        Faculty faculty = Faculty.builder()
                .setNameUa(null)
                .setNameEn(null)
                .setDescriptionUa(null)
                .setDescriptionEn(null)
                .setStateFundedPlaces(null)
                .setContractPlaces(null)
                .build();

        Assert.assertEquals(expected, ValidationUtil.getFacultyDataErrors(faculty));
    }


    @Test
    public void ShouldReturnStringWithErrorsWhenFacultyHasTooSmallValuesInVariables() {
        String expected = "nameEnError&nameUaError&descriptionEnError&descriptionUaError&stateFundedPlacesError&contractPlacesError";
        Faculty faculty = Faculty.builder()
                .setNameUa("a")
                .setNameEn("a")
                .setDescriptionUa(TEST_STRING.substring(0, 9))
                .setDescriptionEn(TEST_STRING.substring(0, 9))
                .setStateFundedPlaces(-1)
                .setContractPlaces(-1)
                .build();

        Assert.assertEquals(expected, ValidationUtil.getFacultyDataErrors(faculty));
    }

    @Test
    public void ShouldReturnEmptyStringWhenFacultyHasMinValuesInVariables() {
        Faculty faculty = Faculty.builder()
                .setNameUa("AA")
                .setNameEn("AA")
                .setDescriptionUa(TEST_STRING.substring(0, 10))
                .setDescriptionEn(TEST_STRING.substring(0, 10))
                .setStateFundedPlaces(0)
                .setContractPlaces(0)
                .build();

        Assert.assertTrue(ValidationUtil.getFacultyDataErrors(faculty).isEmpty());
    }

    @Test
    public void ShouldReturnEmptyStringWhenFacultyHasMaxValuesInVariables() {
        Faculty faculty = Faculty.builder()
                .setNameUa(TEST_STRING.substring(0, 250))
                .setNameEn(TEST_STRING.substring(0, 250))
                .setDescriptionUa(TEST_STRING)
                .setDescriptionEn(TEST_STRING)
                .setStateFundedPlaces(1)
                .setContractPlaces(1)
                .build();

        Assert.assertTrue(ValidationUtil.getFacultyDataErrors(faculty).isEmpty());
    }

    @Test
    public void ShouldReturnStringWithErrorsWhenFacultyHasTooLongValuesInVariables() {
        String expected = "nameEnError&nameUaError";
        Faculty faculty = Faculty.builder()
                .setNameUa(TEST_STRING.substring(0, 251))
                .setNameEn(TEST_STRING.substring(0, 251))
                .setDescriptionUa(TEST_STRING)
                .setDescriptionEn(TEST_STRING)
                .setStateFundedPlaces(1)
                .setContractPlaces(1)
                .build();

        Assert.assertEquals(expected, ValidationUtil.getFacultyDataErrors(faculty));
    }
}
