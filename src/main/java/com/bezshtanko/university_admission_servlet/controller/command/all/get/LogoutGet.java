package com.bezshtanko.university_admission_servlet.controller.command.all.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.controller.command.all.post.LoginPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class LogoutGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(LogoutGet.class);

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
