package com.bezshtanko.university_admission_servlet.controller.command.entrant.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.filter.AuthFilter;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.mark.Mark;
import com.bezshtanko.university_admission_servlet.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

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
        UserDTO user = (UserDTO) session.getAttribute(AuthFilter.AUTH_ATTRIBUTE_NAME);

        Faculty faculty = (Faculty) session.getAttribute("faculty");
        session.removeAttribute("faculty");

        BigDecimal zero = new BigDecimal("0");
        List<Mark> marks = faculty.getSubjects()
                .stream()
                .map(s -> Mark.builder()
                        .setSubject(s)
                        .setMark(new BigDecimal(request.getParameter(s.getNameEn() + " " + s.getType()))
                                .setScale(2, RoundingMode.DOWN))
                        .build())
                .peek(m -> {
                    if (m.getMark().compareTo(zero) < 0) {
                        throw new RuntimeException("Mark must be equal or greater than 0");
                    }
                }).collect(Collectors.toList());

        enrollmentService.save(user, faculty, marks);
        return "redirect:/entrant/faculty?facultyId=" + faculty.getId();
    }
}
