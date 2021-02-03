package com.bezshtanko.university_admission_servlet.controller.command.all.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.exception.AuthenticationException;
import com.bezshtanko.university_admission_servlet.model.user.User;
import com.bezshtanko.university_admission_servlet.model.user.UserRole;
import com.bezshtanko.university_admission_servlet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginPost implements Command {

    private static final Logger log = LoggerFactory.getLogger(LoginPost.class);

    private final UserService userService;

    public LoginPost(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing login post command");

        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserDTO user;
        try {
            user = userService.login(email, password);
            log.info("User {} was found in database", user);
        } catch (AuthenticationException e) {
            log.info("User with email \"{}\" was not found", email);
            return "redirect:/login?error";
        }

        if (user.isBlocked()) {
            log.info("User {} is blocked", user);
            return "redirect:/login?userBlocked";
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        if (user.hasRole(UserRole.ENTRANT) && (user.isEnrolledContract() || user.isEnrolledStateFunded())) {
            return "entrant/congratulation";
        }

        log.info("redirecting to faculties");
        return user.hasRole(UserRole.ADMIN)
                ? "redirect:/admin/faculties"
                : "redirect:/entrant/faculties";

    }
}
