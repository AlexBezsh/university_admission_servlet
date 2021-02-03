package com.bezshtanko.university_admission_servlet.controller.command.admin.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.faculty.FacultyStatus;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import com.bezshtanko.university_admission_servlet.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminFacultyEditPost implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultyEditPost.class);

    private final FacultyService facultyService;

    public AdminFacultyEditPost(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing admin faculty edit post command");

        Faculty faculty = Faculty.builder()
                .setId(Long.parseLong(request.getParameter("facultyId")))
                .setNameEn(request.getParameter("nameEn"))
                .setNameUa(request.getParameter("nameUa"))
                .setDescriptionEn(request.getParameter("descriptionEn"))
                .setDescriptionUa(request.getParameter("descriptionUa"))
                .setStateFundedPlaces(Integer.parseInt(request.getParameter("stateFundedPlaces")))
                .setContractPlaces(Integer.parseInt(request.getParameter("contractPlaces")))
                .build();

        String errors = ValidationUtil.getFacultyErrors(faculty);
        if (!errors.isEmpty()) {
            log.info("There are errors in received data");
            for (String error : errors.split("&")) {
                request.setAttribute(error, error);
            }
            return "admin/edit_faculty_form";
        }
        request.getSession().removeAttribute("facultyToEdit");

        facultyService.update(faculty);
        return "redirect:/admin/faculties";
    }
}
