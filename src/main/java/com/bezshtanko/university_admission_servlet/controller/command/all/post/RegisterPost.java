package com.bezshtanko.university_admission_servlet.controller.command.all.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.model.user.User;
import com.bezshtanko.university_admission_servlet.model.user.UserRole;
import com.bezshtanko.university_admission_servlet.model.user.UserStatus;
import com.bezshtanko.university_admission_servlet.service.UserService;
import com.bezshtanko.university_admission_servlet.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;

public class RegisterPost implements Command {

    private static final Logger log = LoggerFactory.getLogger(RegisterPost.class);

    private final UserService userService;

    public RegisterPost(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing register post command");

        User user = User.builder()
                .setFullName(request.getParameter("fullName"))
                .setEmail(request.getParameter("email"))
                .setPassword(request.getParameter("password"))
                .setStatus(UserStatus.ACTIVE)
                .setRoles(new HashSet<>(Collections.singletonList(UserRole.ENTRANT)))
                .setCity(request.getParameter("city"))
                .setRegion(request.getParameter("region"))
                .setEducation(request.getParameter("education"))
                .build();

        String errors = ValidationUtil.getUserErrors(user);
        if (!errors.isEmpty()) {
            log.info("There are errors in received data");
            return "redirect:/register?" + errors;
        }

        userService.saveNewUser(user);
        return "redirect:/login?registrationSuccess";
    }
}
