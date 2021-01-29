package com.bezshtanko.university_admission_servlet.controller.command.all.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class RegisterPost implements Command {

    private static final Logger log = LoggerFactory.getLogger(RegisterPost.class);

    @Override
    public String execute(HttpServletRequest request) {
        //don't forget about password matching to pattern
        return null;
    }
}
