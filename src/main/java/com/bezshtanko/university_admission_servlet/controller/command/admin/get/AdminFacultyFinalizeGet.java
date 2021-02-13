package com.bezshtanko.university_admission_servlet.controller.command.admin.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminFacultyFinalizeGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultyFinalizeGet.class);

    private final FacultyService facultyService;

    public AdminFacultyFinalizeGet(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing admin faculty finalize get command");
        Long facultyId = Long.parseLong(request.getParameter("facultyId"));
        facultyService.finalizeFaculty(facultyId);
        return "redirect:/admin/faculty?facultyId=" + facultyId;
    }
}
