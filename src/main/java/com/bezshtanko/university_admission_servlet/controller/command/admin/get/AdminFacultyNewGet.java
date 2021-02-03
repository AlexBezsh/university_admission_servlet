package com.bezshtanko.university_admission_servlet.controller.command.admin.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;
import com.bezshtanko.university_admission_servlet.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminFacultyNewGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultyNewGet.class);

    private final SubjectService subjectService;

    public AdminFacultyNewGet(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing admin faculty new get command");
        request.getSession().setAttribute("subjects",subjectService.findAll());
        return "admin/create_faculty_form";
    }
}
