package com.bezshtanko.university_admission_servlet;

import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.enrollment.EnrollmentStatus;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.faculty.FacultyStatus;
import com.bezshtanko.university_admission_servlet.model.mark.Mark;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;
import com.bezshtanko.university_admission_servlet.model.subject.SubjectType;
import com.bezshtanko.university_admission_servlet.model.user.User;
import com.bezshtanko.university_admission_servlet.model.user.UserRole;
import com.bezshtanko.university_admission_servlet.model.user.UserStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestData {

    public static final Subject TEST_SUBJECT_1 = Subject.builder()
            .setId(1L)
            .setType(SubjectType.EXAM)
            .setNameUa("Математика")
            .setNameEn("Mathematics")
            .build();

    public static final Subject TEST_SUBJECT_2 = Subject.builder()
            .setId(2L)
            .setType(SubjectType.SCHOOL)
            .setNameUa("Англійська мова")
            .setNameEn("English")
            .build();


    public static final Faculty TEST_FACULTY = Faculty.builder()
            .setId(1L)
            .setNameEn("Faculty")
            .setNameUa("Факультет")
            .setStatus(FacultyStatus.ACTIVE)
            .setDescriptionEn("Description in English")
            .setDescriptionUa("Опис українською")
            .setStateFundedPlaces(20)
            .setContractPlaces(30)
            .setSubjects(Arrays.asList(TEST_SUBJECT_1, TEST_SUBJECT_2))
            .build();

    public static final User TEST_USER = User.builder()
            .setId(1L)
            .setFullName("User Full Name")
            .setEmail("email@e")
            .setPassword("Strong1")
            .setStatus(UserStatus.ACTIVE)
            .setRoles(Collections.singleton(UserRole.ENTRANT))
            .setCity("City")
            .setRegion("Region")
            .setEducation("Education")
            .build();

    public static final String TEST_USER_HASHED_PASSWORD = "1c35a06d1808c8a50aff5e2d6adc1beafcb708ffae002feb578550a4ffe38afc";


    public static final Mark TEST_MARK_1 = Mark.builder()
            .setId(1L)
            .setSubject(TEST_SUBJECT_1)
            .setMark(new BigDecimal("90"))
            .build();


    public static final Mark TEST_MARK_2 = Mark.builder()
            .setId(1L)
            .setSubject(TEST_SUBJECT_2)
            .setMark(new BigDecimal("100"))
            .build();


    public static final Enrollment TEST_ENROLLMENT = Enrollment.builder()
            .setId(1L)
            .setUser(TEST_USER)
            .setFaculty(TEST_FACULTY)
            .setStatus(EnrollmentStatus.NEW)
            .setMarks(Arrays.asList(TEST_MARK_1, TEST_MARK_2))
            .build();

    static {
        List<Enrollment> enrollments = Collections.singletonList(TEST_ENROLLMENT);
        TEST_FACULTY.setEnrollments(enrollments);
        TEST_USER.setEnrollments(enrollments);
        TEST_ENROLLMENT.setUser(TEST_USER);
        TEST_MARK_1.setEnrollment(TEST_ENROLLMENT);
        TEST_MARK_2.setEnrollment(TEST_ENROLLMENT);
    }

    public static final UserDTO TEST_USER_DTO = new UserDTO(TEST_USER);

}
