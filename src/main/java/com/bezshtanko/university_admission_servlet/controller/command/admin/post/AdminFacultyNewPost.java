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

        Faculty faculty = retrieveFaculty(request);
        List<Subject> chosenSubjects = retrieveChosenSubjects(request);

        String errors = ValidationUtil.getFacultyDataErrors(faculty);
        if (chosenSubjects.isEmpty()) {
            errors = "subjectsError&" + errors;
        } else {
            faculty.setSubjects(chosenSubjects);
        }

        if (!errors.isEmpty()) {
            log.info("There are errors in received data");
            return "redirect:/admin/faculty/new?" + errors;
        }

        facultyService.save(faculty);
        return "redirect:/admin/faculties";
    }

    private Faculty retrieveFaculty(HttpServletRequest request) {
        return Faculty.builder()
                .setNameEn(request.getParameter("nameEn"))
                .setNameUa(request.getParameter("nameUa"))
                .setStatus(FacultyStatus.ACTIVE)
                .setDescriptionEn(request.getParameter("descriptionEn"))
                .setDescriptionUa(request.getParameter("descriptionUa"))
                .setStateFundedPlaces(Integer.parseInt(request.getParameter("stateFundedPlaces")))
                .setContractPlaces(Integer.parseInt(request.getParameter("contractPlaces")))
                .build();
    }

    private List<Subject> retrieveChosenSubjects(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Subject> allSubjects = (List<Subject>) session.getAttribute("subjects");
        session.removeAttribute("subjects");

        return allSubjects.stream()
                .filter(subject -> request.getParameter((subject.getNameEn() + " " + subject.getType())) != null)
                .collect(Collectors.toList());
    }

}
