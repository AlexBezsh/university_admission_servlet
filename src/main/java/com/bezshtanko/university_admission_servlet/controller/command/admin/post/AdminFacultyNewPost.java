package com.bezshtanko.university_admission_servlet.controller.command.admin.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.faculty.FacultyStatus;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
import com.bezshtanko.university_admission_servlet.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

public class AdminFacultyNewPost implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminFacultyNewPost.class);

    private final FacultyService facultyService;

    public AdminFacultyNewPost(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing admin faculty new post command");

        Faculty faculty = Faculty.builder()
                .setNameEn(request.getParameter("nameEn"))
                .setNameUa(request.getParameter("nameUa"))
                .setStatus(FacultyStatus.ACTIVE)
                .setDescriptionEn(request.getParameter("descriptionEn"))
                .setDescriptionUa(request.getParameter("descriptionUa"))
                .setStateFundedPlaces(Integer.parseInt(request.getParameter("stateFundedPlaces")))
                .setContractPlaces(Integer.parseInt(request.getParameter("contractPlaces")))
                .build();

        String errors = ValidationUtil.getFacultyErrors(faculty);
        if (!errors.isEmpty()) {
            log.info("There are errors in received data");
            return "redirect:/admin/faculty/new?" + errors;
        }

        HttpSession session = request.getSession();
        List<Subject> allSubjects = (List<Subject>) session.getAttribute("subjects");
        session.removeAttribute("subjects");
        List<Subject> chosenSubjects = allSubjects.stream()
                .filter(subject -> request.getParameter((subject.getNameEn() + " " + subject.getType())) != null)
                .collect(Collectors.toList());
        faculty.setSubjects(chosenSubjects);

        facultyService.save(faculty);

        return "redirect:/admin/faculties";
    }
}
