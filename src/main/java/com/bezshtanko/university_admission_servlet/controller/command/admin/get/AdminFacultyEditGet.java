package com.bezshtanko.university_admission_servlet.controller.command.admin.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminFacultyEditGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultyEditGet.class);

    private final FacultyService facultyService;

    public AdminFacultyEditGet(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing admin faculty edit get command");
        Long facultyId = Long.parseLong(request.getParameter("facultyId"));
        request.getSession().setAttribute("facultyToEdit", facultyService.findById(facultyId));
        return "admin/edit_faculty_form";
    }
}
