package com.bezshtanko.university_admission_servlet.controller.command.user.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.controller.command.user.get.UserFacultiesGet;
import com.bezshtanko.university_admission_servlet.controller.command.user.get.UserFacultyEnrollGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UserFacultyEnrollPost implements Command {

    private static final Logger log = LoggerFactory.getLogger(UserFacultyEnrollPost.class);

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
