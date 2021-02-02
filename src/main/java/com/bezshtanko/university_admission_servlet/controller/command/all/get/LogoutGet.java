package com.bezshtanko.university_admission_servlet.controller.command.all.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(LogoutGet.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing logout get command");
        HttpSession session = request.getSession();
        log.info("User {} is logging out", session.getAttribute("user"));
        session.removeAttribute("user");
        return "redirect:/";
    }
}
