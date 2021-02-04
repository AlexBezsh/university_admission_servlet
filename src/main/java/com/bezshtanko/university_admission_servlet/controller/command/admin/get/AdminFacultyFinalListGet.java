package com.bezshtanko.university_admission_servlet.controller.command.admin.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminFacultyFinalListGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultyFinalListGet.class);

    private final FacultyService facultyService;

    public AdminFacultyFinalListGet(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing admin faculty final list get command");
        Long facultyId = Long.parseLong(request.getParameter("facultyId"));
        request.setAttribute("faculty", facultyService.findWithFinalList(facultyId));
        System.out.println("Yes, it was here");
        return "final_list";
    }
}
