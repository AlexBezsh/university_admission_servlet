package com.bezshtanko.university_admission_servlet.controller.command.all.get;

import com.bezshtanko.university_admission_servlet.controller.Servlet;
import com.bezshtanko.university_admission_servlet.controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class LoginGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(LoginGet.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("returning login page");
        return "login";
    }
}
