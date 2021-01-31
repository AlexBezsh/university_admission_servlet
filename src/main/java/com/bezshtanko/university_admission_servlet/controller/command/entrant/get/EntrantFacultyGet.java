package com.bezshtanko.university_admission_servlet.controller.command.entrant.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
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
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;

public class EntrantFacultyGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(EntrantFacultyGet.class);

    private final FacultyService facultyService;

    public EntrantFacultyGet(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("executing entrant faculty get command");
        Long facultyId = Long.parseLong(request.getParameter("facultyId"));
        request.setAttribute("faculty", facultyService.getFacultyWithEnrollments(facultyId));
        return "entrant/faculty";
    }


    private Faculty temp() { //TODO delete this method when database will be working
        Faculty faculty = Faculty.builder()
                .setId(1L)
                .setNameEn("f_name_en")
                .setNameUa("f_name_ua")
                .setStatus(FacultyStatus.ACTIVE)
                .setDescriptionEn("description_en")
                .setDescriptionUa("description_ua")
                .setStateFundedPlaces(2)
                .setContractPlaces(3)
                .build();


        Subject subject = Subject.builder()
                .setId(45L)
                .setNameEn("s_name_en")
                .setNameUa("s_name_ua")
                .setType(SubjectType.EXAM)
                .build();

        faculty.getSubjects().add(subject);

        User user = User.builder()
                .setId(56L)
                .setFullName("full_name")
                .setEmail("email")
                .setStatus(UserStatus.ACTIVE)
                .setRoles(new HashSet<>(Collections.singletonList(UserRole.ENTRANT)))
                .setCity("city")
                .setRegion("region")
                .setEducation("education")
                .build();

        Enrollment enrollment = Enrollment.builder()
                .setId(22L)
                .setFaculty(faculty)
                .setUser(user)
                .setStatus(EnrollmentStatus.NEW)
                .build();

        Mark mark = Mark.builder()
                .setId(45L)
                .setMark(new BigDecimal(4))
                .setEnrollment(enrollment)
                .setSubject(subject)
                .build();
        enrollment.setMarks(Collections.singletonList(mark));
        faculty.getEnrollments().add(enrollment);
        return faculty;
    }
}
