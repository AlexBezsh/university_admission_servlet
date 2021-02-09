package com.bezshtanko.university_admission_servlet.controller.command.entrant.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.filter.AuthFilter;
import com.bezshtanko.university_admission_servlet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class EntrantProfileGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(EntrantProfileGet.class);

    private final UserService userService;

    public EntrantProfileGet(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing entrant profile get command");
        UserDTO user = (UserDTO) request.getSession().getAttribute(AuthFilter.AUTH_ATTRIBUTE_NAME);
        request.setAttribute("user", userService.getWithEnrollments(user.getId()));
        return "user_profile";
    }
}
