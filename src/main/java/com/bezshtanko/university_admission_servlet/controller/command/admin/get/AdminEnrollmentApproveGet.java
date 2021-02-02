package com.bezshtanko.university_admission_servlet.controller.command.admin.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminEnrollmentApproveGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminEnrollmentApproveGet.class);

    private final EnrollmentService enrollmentService;

    public AdminEnrollmentApproveGet(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing admin enrollment approve get command");
        Long enrollmentId = Long.parseLong(request.getParameter("enrollmentId"));
        enrollmentService.setApproved(enrollmentId);
        return "redirect:/admin/faculty?facultyId=" + Long.parseLong(request.getParameter("facultyId"));
    }
}
