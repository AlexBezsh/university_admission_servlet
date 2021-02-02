package com.bezshtanko.university_admission_servlet.controller.command.admin.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminFacultyEditPost implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultyEditPost.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing admin faculty edit post command");
        return null;
    }
}
