package com.bezshtanko.university_admission_servlet.controller.command.entrant.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.filter.AuthFilter;
import com.bezshtanko.university_admission_servlet.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class EntrantProfileGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(EntrantProfileGet.class);

    private final EnrollmentService enrollmentService;

    public EntrantProfileGet(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing entrant profile get command");
        UserDTO user = (UserDTO) request.getSession().getAttribute(AuthFilter.USER_SESSION_ATTRIBUTE_NAME);
        user.setEnrollments(enrollmentService.findAllByUserId(user.getId()));
        request.setAttribute("user", user);
        return "user_profile";
    }
}
