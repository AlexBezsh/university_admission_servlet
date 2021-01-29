package com.bezshtanko.university_admission_servlet.controller.command.all.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class ErrorGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(ErrorGet.class);

    @Override
    public String execute(HttpServletRequest request) {
        return "error";
    }
}
