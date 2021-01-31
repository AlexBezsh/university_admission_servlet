package com.bezshtanko.university_admission_servlet.controller.command.admin.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminFacultyEditGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultyEditGet.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("executing admin faculty edit get command");
        return null;
    }
}
