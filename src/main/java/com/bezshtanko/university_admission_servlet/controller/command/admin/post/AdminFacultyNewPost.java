package com.bezshtanko.university_admission_servlet.controller.command.admin.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.faculty.FacultyStatus;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;
import com.bezshtanko.university_admission_servlet.service.FacultyService;
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

        String errors = "?";

        String nameEn = request.getParameter("nameEn");
        if (nameEn == null || nameEn.length() < 2 || nameEn.length() > 250) {
            errors += "nameEnError&";
        }

        String nameUa = request.getParameter("nameUa");
        if (nameUa == null || nameUa.length() < 2 || nameUa.length() > 250) {
            errors += "nameUaError&";
        }

        String descriptionEn = request.getParameter("descriptionEn");
        if (descriptionEn == null || descriptionEn.length() < 10) {
            errors += "descriptionEnError&";
        }

        String descriptionUa = request.getParameter("descriptionUa");
        if (descriptionUa == null || descriptionUa.length() < 10) {
            errors += "descriptionUaError&";
        }

        int stateFundedPlaces = Integer.parseInt(request.getParameter("stateFundedPlaces"));
        if (stateFundedPlaces < 0) {
            errors += "stateFundedPlacesError&";
        }

        int contractPlaces = Integer.parseInt(request.getParameter("contractPlaces"));
        if (contractPlaces < 0) {
            errors += "contractPlacesError&";
        }

        if (!errors.equals("?")) {
            log.info("There are errors in received data");
            return "redirect:/admin/faculty/new" + errors;
        }

        HttpSession session = request.getSession();
        List<Subject> allSubjects = (List<Subject>) session.getAttribute("subjects");
        session.removeAttribute("subjects");
        List<Subject> chosenSubjects = allSubjects.stream()
                .filter(subject -> request.getParameter((subject.getNameEn() + " " + subject.getType())) != null)
                .collect(Collectors.toList());

        facultyService.save(Faculty.builder()
                .setNameEn(nameEn)
                .setNameUa(nameUa)
                .setStatus(FacultyStatus.ACTIVE)
                .setDescriptionEn(descriptionEn)
                .setDescriptionUa(descriptionUa)
                .setStateFundedPlaces(stateFundedPlaces)
                .setContractPlaces(contractPlaces)
                .setSubjects(chosenSubjects)
                .build());

        return "redirect:/admin/faculties";
    }
}
