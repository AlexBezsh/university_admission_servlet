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
        try {
            User userFromDB = userService.login(request.getParameter("email"), request.getParameter("password"));
            log.info("user {} was found in database", userFromDB);
            HttpSession session = request.getSession();
            session.setAttribute("user", new UserDTO(userFromDB));
            log.info("redirecting to faculties");
            return userFromDB.hasRole(UserRole.ADMIN)
                    ? "redirect:/admin/faculties"
                    : "redirect:/user/faculties";
        } catch (AuthenticationException e) {
            log.info("user with email \"{}\" was not found", request.getParameter("email"));
            return "redirect:/login?error";
        }
    }
}
