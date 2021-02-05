package com.bezshtanko.university_admission_servlet.controller;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.controller.command.admin.get.*;
import com.bezshtanko.university_admission_servlet.controller.command.admin.post.AdminFacultyEditPost;
import com.bezshtanko.university_admission_servlet.controller.command.admin.post.AdminFacultyNewPost;
import com.bezshtanko.university_admission_servlet.controller.command.all.get.*;
import com.bezshtanko.university_admission_servlet.controller.command.all.post.LoginPost;
import com.bezshtanko.university_admission_servlet.controller.command.all.post.RegisterPost;
import com.bezshtanko.university_admission_servlet.controller.command.entrant.get.*;
import com.bezshtanko.university_admission_servlet.controller.command.entrant.post.EntrantEnrollPost;
import com.bezshtanko.university_admission_servlet.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Servlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(Servlet.class);

    private final Map<String, Command> commands = new HashMap<>();
    private static final String TEMPLATES_PATH = "/WEB-INF/jsp/";

    @Override
    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext().setAttribute("loggedUsers", ConcurrentHashMap.newKeySet());

        commands.put("/ get", new HomeGet());
        commands.put("/login get", new LoginGet());
        commands.put("/login post", new LoginPost((UserService) Services.USER_SERVICE.get()));
        commands.put("/logout get", new LogoutGet());
        commands.put("/register get", new RegisterGet());
        commands.put("/register post", new RegisterPost((UserService) Services.USER_SERVICE.get()));
        commands.put("/error get", new ErrorGet());

        commands.put("/entrant/profile get", new EntrantProfileGet((EnrollmentService) Services.ENROLLMENT_SERVICE.get()));
        commands.put("/entrant/faculties get", new EntrantFacultiesGet((FacultyService) Services.FACULTY_SERVICE.get()));
        commands.put("/entrant/faculty get", new EntrantFacultyGet((FacultyService) Services.FACULTY_SERVICE.get()));
        commands.put("/entrant/enroll get", new EntrantEnrollGet((FacultyService) Services.FACULTY_SERVICE.get()));
        commands.put("/entrant/enroll post", new EntrantEnrollPost((EnrollmentService) Services.ENROLLMENT_SERVICE.get()));
        commands.put("/entrant/congratulation get", new EntrantCongratulationGet());

        commands.put("/admin/faculties get", new AdminFacultiesGet((FacultyService) Services.FACULTY_SERVICE.get()));
        commands.put("/admin/faculty get", new AdminFacultyGet((FacultyService) Services.FACULTY_SERVICE.get()));
        commands.put("/admin/faculty/new get", new AdminFacultyNewGet((SubjectService) Services.SUBJECT_SERVICE.get()));
        commands.put("/admin/faculty/new post", new AdminFacultyNewPost((FacultyService) Services.FACULTY_SERVICE.get()));
        commands.put("/admin/faculty/edit get", new AdminFacultyEditGet((FacultyService) Services.FACULTY_SERVICE.get()));
        commands.put("/admin/faculty/edit post", new AdminFacultyEditPost((FacultyService) Services.FACULTY_SERVICE.get()));
        commands.put("/admin/faculty/delete get", new AdminFacultyDeleteGet((FacultyService) Services.FACULTY_SERVICE.get()));
        commands.put("/admin/enrollment/approve get", new AdminEnrollmentApproveGet((EnrollmentService) Services.ENROLLMENT_SERVICE.get()));
        commands.put("/admin/faculty/finalize get", new AdminFacultyFinalizeGet((FacultyService) Services.FACULTY_SERVICE.get()));
        commands.put("/admin/user get", new AdminUserGet((UserService) Services.USER_SERVICE.get()));
        commands.put("/admin/user/block get", new AdminUserBlockGet((UserService) Services.USER_SERVICE.get()));
        commands.put("/admin/user/unblock get", new AdminUserUnblockGet((UserService) Services.USER_SERVICE.get()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Get request received");
        processRequest(request, response, " get");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Post request received");
        processRequest(request, response, " post");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, String requestMethod) throws ServletException, IOException {
        String page = commands.get(request.getRequestURI() + requestMethod).execute(request);
        log.info("Page \"{}\" was created", page);

        if (page.startsWith("redirect:")) {
            log.info("redirecting");
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            log.info("forwarding");
            request.getRequestDispatcher( TEMPLATES_PATH + page + ".jsp").forward(request, response);
        }
    }

}
