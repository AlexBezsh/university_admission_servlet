package com.bezshtanko.university_admission_servlet.controller.command.all.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.model.user.User;
import com.bezshtanko.university_admission_servlet.model.user.UserRole;
import com.bezshtanko.university_admission_servlet.model.user.UserStatus;
import com.bezshtanko.university_admission_servlet.service.UserService;
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
        log.info("executing register post command");

        String errors = "?";

        String fullName = request.getParameter("fullName");
        if (fullName == null || fullName.length() < 5 || fullName.length() > 120) {
            errors += "fullNameError&";
        }

        String email = request.getParameter("email");
        if (!email.matches(".+@.+")) {
            errors += "emailError&";

        }

        String password = request.getParameter("password");
        if (!password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+).{6,}")) {
            errors += "passwordError&";
        }

        String city = request.getParameter("city");
        if (city == null || city.length() < 2 || city.length() > 100) {
            errors += "cityError&";
        }

        String region = request.getParameter("region");
        if (region == null || region.length() < 2 || region.length() > 100) {
            errors += "regionError&";
        }

        String education = request.getParameter("education");
        if (education == null || education.length() < 2 || education.length() > 100) {
            errors += "educationError&";
        }

        if (!errors.equals("?")) {
            log.info("there are errors in received data");
            return "redirect:register" + errors;
        }

        userService.saveNewUser(User.builder()
                .setFullName(fullName)
                .setEmail(email)
                .setPassword(password)
                .setStatus(UserStatus.ACTIVE)
                .setRoles(new HashSet<>(Collections.singletonList(UserRole.ENTRANT)))
                .setCity(city)
                .setRegion(region)
                .setEducation(education)
                .build());

        return "redirect:/login?registrationSuccess";
    }
}
