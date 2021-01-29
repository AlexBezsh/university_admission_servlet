package com.bezshtanko.university_admission_servlet.controller.command.user.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UserFacultiesGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(UserFacultiesGet.class);

    @Override
    public String execute(HttpServletRequest request) {
        return "user/faculties";
    }
}
