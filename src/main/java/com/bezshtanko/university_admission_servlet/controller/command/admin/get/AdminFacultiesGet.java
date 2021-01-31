package com.bezshtanko.university_admission_servlet.controller.command.admin.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminFacultiesGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultiesGet.class);


    @Override
    public String execute(HttpServletRequest request) {
        log.info("executing admin faculties get command");
        return "admin/faculties";
    }
}
