package com.bezshtanko.university_admission_servlet.controller.command.entrant.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class EntrantCongratulationGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(EntrantCongratulationGet.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing entrant congratulation get command");
        return "entrant/congratulation";
    }
}
