package com.bezshtanko.university_admission_servlet.controller.command.admin.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminFacultyNewPost implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultyNewPost.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("executing admin faculty new post command");

        return null;
    }
}
