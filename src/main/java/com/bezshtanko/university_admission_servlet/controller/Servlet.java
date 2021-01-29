package com.bezshtanko.university_admission_servlet.controller;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.controller.command.admin.get.*;
import com.bezshtanko.university_admission_servlet.controller.command.admin.post.AdminFacultyEditPost;
import com.bezshtanko.university_admission_servlet.controller.command.admin.post.AdminFacultyNewPost;
import com.bezshtanko.university_admission_servlet.controller.command.all.get.*;
import com.bezshtanko.university_admission_servlet.controller.command.all.post.LoginPost;
import com.bezshtanko.university_admission_servlet.controller.command.all.post.RegisterPost;
import com.bezshtanko.university_admission_servlet.controller.command.entrant.get.EntrantFacultiesGet;
import com.bezshtanko.university_admission_servlet.controller.command.entrant.get.EntrantFacultyEnrollGet;
import com.bezshtanko.university_admission_servlet.controller.command.entrant.get.EntrantFacultyGet;
import com.bezshtanko.university_admission_servlet.controller.command.entrant.get.EntrantProfileGet;
import com.bezshtanko.university_admission_servlet.controller.command.entrant.post.EntrantFacultyEnrollPost;
import com.bezshtanko.university_admission_servlet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(Servlet.class);

    private final Map<String, Command> commands = new HashMap<>();
    private static final String TEMPLATES_PATH = "/WEB-INF/jsp/";

    @Override
    public void init() {
        commands.put("/ get", new HomeGet());
        commands.put("/login get", new LoginGet());
        commands.put("/login post", new LoginPost(new UserService()));
        commands.put("/logout get", new LogoutGet());
        commands.put("/register get", new RegisterGet());
        commands.put("/register post", new RegisterPost());
        commands.put("/error get", new ErrorGet());

        commands.put("/entrant/profile get", new EntrantProfileGet());
        commands.put("/entrant/faculties get", new EntrantFacultiesGet());
        commands.put("/entrant/faculty get", new EntrantFacultyGet());
        commands.put("/entrant/faculty/enroll get", new EntrantFacultyEnrollGet());
        commands.put("/entrant/faculty/enroll post", new EntrantFacultyEnrollPost());

        commands.put("/admin/faculties get", new AdminFacultiesGet());
        commands.put("/admin/faculty get", new AdminFacultyGet());
        commands.put("/admin/faculty/new get", new AdminFacultyNewGet());
        commands.put("/admin/faculty/new post", new AdminFacultyNewPost());
        commands.put("/admin/faculty/edit get", new AdminFacultyEditGet());
        commands.put("/admin/faculty/edit post", new AdminFacultyEditPost());
        commands.put("/admin/faculty/delete get", new AdminFacultyDeleteGet());
        commands.put("/admin/faculty/enrollment/approve get", new AdminFacultyEnrollmentApproveGet());
        commands.put("/admin/faculty/finalize get", new AdminFacultyFinalizeGet());
        commands.put("/admin/user get", new AdminUserGet());
        commands.put("admin/user/block get", new AdminUserBlockGet());
        commands.put("admin/user/unblock get", new AdminUserUnblockGet());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("get request received");
        processRequest(request, response, " get");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("post request received");
        processRequest(request, response, " post");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, String requestMethod) throws ServletException, IOException {
        String page = commands.get(request.getRequestURI() + requestMethod).execute(request);
        log.info("page \"{}\" was created", page);

        if (page.startsWith("redirect:")) {
            log.info("redirecting");
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            log.info("forwarding");
            request.getRequestDispatcher( TEMPLATES_PATH + page + ".jsp").forward(request, response);
        }
    }

}
