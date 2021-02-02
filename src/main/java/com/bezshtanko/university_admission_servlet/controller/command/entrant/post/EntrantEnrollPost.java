package com.bezshtanko.university_admission_servlet.controller.command.entrant.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.enrollment.EnrollmentStatus;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.mark.Mark;
import com.bezshtanko.university_admission_servlet.model.user.User;
import com.bezshtanko.university_admission_servlet.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class EntrantEnrollPost implements Command {

    private static final Logger log = LoggerFactory.getLogger(EntrantEnrollPost.class);

    private final EnrollmentService enrollmentService;

    public EntrantEnrollPost(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing entrant enroll post command");
        HttpSession session = request.getSession();
        Faculty faculty = (Faculty) session.getAttribute("faculty");
        session.removeAttribute("faculty");

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
        //todo check for positive values in marks

        enrollmentService.save(enrollment);

        UserDTO user = (UserDTO) session.getAttribute("user");
        user.getEnrollments().add(enrollment);

        return "redirect:/entrant/faculty?facultyId=" + faculty.getId();
    }
}
