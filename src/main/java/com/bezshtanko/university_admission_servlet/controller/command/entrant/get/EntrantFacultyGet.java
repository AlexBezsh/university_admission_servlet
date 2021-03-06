package com.bezshtanko.university_admission_servlet.controller.command.entrant.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class EntrantFacultyGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(EntrantFacultyGet.class);

    private final FacultyService facultyService;

    public EntrantFacultyGet(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing entrant faculty get command");
        Long facultyId = Long.parseLong(request.getParameter("facultyId"));
        request.setAttribute("faculty", facultyService.findWithEnrollments(facultyId));
        return "faculty";
    }

}
