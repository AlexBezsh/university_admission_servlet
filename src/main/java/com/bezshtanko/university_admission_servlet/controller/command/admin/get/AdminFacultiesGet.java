package com.bezshtanko.university_admission_servlet.controller.command.admin.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminFacultiesGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultiesGet.class);

    private final FacultyService facultyService;

    public AdminFacultiesGet(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing admin faculties get command");
        request.setAttribute("faculties", facultyService.findAll());
        return "admin/faculties";
    }
}
