package com.bezshtanko.university_admission_servlet.controller.command.all.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.exception.AuthenticationException;
import com.bezshtanko.university_admission_servlet.filter.AuthFilter;
import com.bezshtanko.university_admission_servlet.listener.SessionListener;
import com.bezshtanko.university_admission_servlet.model.user.UserRole;
import com.bezshtanko.university_admission_servlet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

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
        if (checkUserIsLogged(session, user)) {
            return "redirect:/login?userIsLogged";
        }

        session.setAttribute(AuthFilter.AUTH_ATTRIBUTE_NAME, user);
        log.info("redirecting to faculties");
        return user.hasRole(UserRole.ADMIN)
                ? "redirect:/admin/faculties"
                : "redirect:/entrant/faculties";
    }

    private boolean checkUserIsLogged(HttpSession session, UserDTO user) {
        Set<String> loggedUsers = (Set<String>) session.getServletContext().getAttribute(SessionListener.LOGGED_USERS_SET_NAME);
        if (loggedUsers.contains(user.getEmail())) {
            log.info("User {} is already logged in", user);
            return true;
        }
        loggedUsers.add(user.getEmail());
        return false;
    }

}
