package com.bezshtanko.university_admission_servlet.controller.command.entrant.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class EntrantFacultyEnrollPost implements Command {

    private static final Logger log = LoggerFactory.getLogger(EntrantFacultyEnrollPost.class);

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
