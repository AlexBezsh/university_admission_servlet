package com.bezshtanko.university_admission_servlet.controller.command.entrant.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.mark.Mark;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class EntrantEnrollGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(EntrantEnrollGet.class);

    private final FacultyService facultyService;

    public EntrantEnrollGet(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing entrant enroll get command");
        Long facultyId = Long.parseLong(request.getParameter("facultyId"));
        Faculty faculty = facultyService.findById(facultyId);
        request.getSession().setAttribute("faculty", faculty);

        Enrollment enrollment = new Enrollment();
        enrollment.setFaculty(faculty);
        enrollment.setMarks(faculty.getSubjects().stream()
                .map(s -> new Mark(null, enrollment, s, new BigDecimal("0")))
                .collect(Collectors.toList()));
        request.setAttribute("enrollment", enrollment);
        return "entrant/enrollment_form";
    }
}
