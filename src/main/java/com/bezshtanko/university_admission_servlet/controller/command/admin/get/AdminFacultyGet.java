package com.bezshtanko.university_admission_servlet.controller.command.admin.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminFacultyGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultyGet.class);

    private final FacultyService facultyService;

    public AdminFacultyGet(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing admin faculty get command");
        Long facultyId = Long.parseLong(request.getParameter("facultyId"));
        request.setAttribute("faculty", facultyService.getWithEnrollments(facultyId));
        return "admin/faculty";
    }
}
