package com.bezshtanko.university_admission_servlet.controller.command.entrant.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.enrollment.EnrollmentStatus;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.mark.Mark;
import com.bezshtanko.university_admission_servlet.model.user.User;
import com.bezshtanko.university_admission_servlet.service.EnrollmentService;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class EntrantEnrollPost implements Command {

    private static final Logger log = LoggerFactory.getLogger(EntrantEnrollPost.class);

    private final FacultyService facultyService;
    private final EnrollmentService enrollmentService;

    public EntrantEnrollPost(FacultyService facultyService, EnrollmentService enrollmentService) {
        this.facultyService = facultyService;
        this.enrollmentService = enrollmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("executing entrant enroll post command");
        Long facultyId = Long.parseLong(request.getParameter("facultyId"));
        Faculty faculty = facultyService.findById(facultyId);
        Enrollment enrollment = new Enrollment();
        enrollment.setFaculty(faculty);
        enrollment.setStatus(EnrollmentStatus.NEW);
        enrollment.setUser(User.builder()
                .setId(((UserDTO) request.getSession().getAttribute("user")).getId())
                .build());

        faculty.getSubjects().forEach(subject ->
                enrollment.getMarks().add(Mark.builder()
                        .setEnrollment(enrollment)
                        .setSubject(subject)
                        .setMark(new BigDecimal(request.getParameter(subject.getNameEn() + " " + subject.getType()))
                                .setScale(2, RoundingMode.DOWN))
                        .build()));

        enrollmentService.save(enrollment);

        return "redirect:/entrant/faculty?facultyId=" + faculty.getId();
    }
}
