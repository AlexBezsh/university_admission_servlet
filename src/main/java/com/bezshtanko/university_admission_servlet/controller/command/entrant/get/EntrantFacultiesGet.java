package com.bezshtanko.university_admission_servlet.controller.command.entrant.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class EntrantFacultiesGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(EntrantFacultiesGet.class);

    private final FacultyService facultyService;

    public EntrantFacultiesGet(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing entrant faculties get command");

        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if (user.isEnrolledContract() || user.isEnrolledStateFunded()) {
            return "entrant/congratulation";
        }

        request.setAttribute("faculties", facultyService.findAll());
        return "entrant/faculties";
    }
}
