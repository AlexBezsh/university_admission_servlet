package com.bezshtanko.university_admission_servlet.controller.command.entrant.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class EntrantFacultyEnrollGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(EntrantFacultyEnrollGet.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("executing entrant faculty enroll get command");
        //TODO take id from session attribute, not from request - in fact it is useless in request
        return null;
    }
}
