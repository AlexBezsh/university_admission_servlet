package com.bezshtanko.university_admission_servlet.controller.command.admin.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.dto.PageInfoDTO;
import com.bezshtanko.university_admission_servlet.filter.FacultiesPaginationFilter;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminFacultiesGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultiesGet.class);

    private final FacultyService facultyService;

    public AdminFacultiesGet(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing admin faculties get command");
        HttpSession session = request.getSession();
        PageInfoDTO pageInfo = (PageInfoDTO) session.getAttribute(FacultiesPaginationFilter.FACULTIES_PAGE_INFO_ATTRIBUTE_NAME);
        request.setAttribute("faculties", facultyService.findAll(pageInfo));
        return "admin/faculties";
    }
}
