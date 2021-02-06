package com.bezshtanko.university_admission_servlet.controller.command.entrant.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.dto.PageInfoDTO;
import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.filter.AuthFilter;
import com.bezshtanko.university_admission_servlet.filter.FacultiesPaginationFilter;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EntrantFacultiesGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(EntrantFacultiesGet.class);

    private final FacultyService facultyService;

    public EntrantFacultiesGet(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing entrant faculties get command");

        HttpSession session = request.getSession();

        UserDTO user = (UserDTO) session.getAttribute(AuthFilter.USER_SESSION_ATTRIBUTE_NAME);
        if (user.isEnrolledContract() || user.isEnrolledStateFunded()) {
            return "redirect:/entrant/congratulation";
        }

        PageInfoDTO pageInfo = (PageInfoDTO) session.getAttribute(FacultiesPaginationFilter.FACULTIES_PAGE_INFO_ATTRIBUTE_NAME);
        request.setAttribute("faculties", facultyService.findAll(pageInfo));
        return "faculties";
    }
}
