package com.bezshtanko.university_admission_servlet.controller.command.admin.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminFacultyDeleteGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultyDeleteGet.class);

    private final FacultyService facultyService;

    public AdminFacultyDeleteGet(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing admin faculty delete get command");
        facultyService.deleteById(Long.parseLong(request.getParameter("facultyId")));
        return "redirect:/admin/faculties";
    }
}
