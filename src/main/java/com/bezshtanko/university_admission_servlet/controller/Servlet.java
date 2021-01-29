package com.bezshtanko.university_admission_servlet.controller;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.controller.command.CommandType;
import com.bezshtanko.university_admission_servlet.controller.command.admin.get.*;
import com.bezshtanko.university_admission_servlet.controller.command.admin.post.AdminFacultyEditPost;
import com.bezshtanko.university_admission_servlet.controller.command.admin.post.AdminFacultyNewPost;
import com.bezshtanko.university_admission_servlet.controller.command.all.get.*;
import com.bezshtanko.university_admission_servlet.controller.command.all.post.LoginPost;
import com.bezshtanko.university_admission_servlet.controller.command.all.post.RegisterPost;
import com.bezshtanko.university_admission_servlet.controller.command.user.get.UserFacultiesGet;
import com.bezshtanko.university_admission_servlet.controller.command.user.get.UserFacultyEnrollGet;
import com.bezshtanko.university_admission_servlet.controller.command.user.get.UserFacultyGet;
import com.bezshtanko.university_admission_servlet.controller.command.user.get.UserProfileGet;
import com.bezshtanko.university_admission_servlet.controller.command.user.post.UserFacultyEnrollPost;
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

    private final Map<CommandType, Command> commands = new HashMap<>();
    private static final String TEMPLATES_PATH = "/WEB-INF/jsp/";

    @Override
    public void init() {
        commands.put(CommandType.HOME_GET, new HomeGet());
        commands.put(CommandType.LOGIN_GET, new LoginGet());
        commands.put(CommandType.LOGIN_POST, new LoginPost(new UserService()));
        commands.put(CommandType.LOGOUT_GET, new LogoutGet());
        commands.put(CommandType.REGISTER_GET, new RegisterGet());
        commands.put(CommandType.REGISTER_POST, new RegisterPost());
        commands.put(CommandType.ERROR_GET, new ErrorGet());

        commands.put(CommandType.USER_PROFILE_GET, new UserProfileGet());
        commands.put(CommandType.USER_FACULTIES_GET, new UserFacultiesGet());
        commands.put(CommandType.USER_FACULTY_GET, new UserFacultyGet());
        commands.put(CommandType.USER_FACULTY_ENROLL_GET, new UserFacultyEnrollGet());
        commands.put(CommandType.USER_FACULTY_ENROLL_POST, new UserFacultyEnrollPost());

        commands.put(CommandType.ADMIN_FACULTIES_GET, new AdminFacultiesGet());
        commands.put(CommandType.ADMIN_FACULTY_GET, new AdminFacultyGet());
        commands.put(CommandType.ADMIN_FACULTY_NEW_GET, new AdminFacultyNewGet());
        commands.put(CommandType.ADMIN_FACULTY_NEW_POST, new AdminFacultyNewPost());
        commands.put(CommandType.ADMIN_FACULTY_EDIT_GET, new AdminFacultyEditGet());
        commands.put(CommandType.ADMIN_FACULTY_EDIT_POST, new AdminFacultyEditPost());
        commands.put(CommandType.ADMIN_FACULTY_DELETE_GET, new AdminFacultyDeleteGet());
        commands.put(CommandType.ADMIN_FACULTY_ENROLLMENT_APPROVE_GET, new AdminFacultyEnrollmentApproveGet());
        commands.put(CommandType.ADMIN_FACULTY_FINALIZE_GET, new AdminFacultyFinalizeGet());
        commands.put(CommandType.ADMIN_USER_GET, new AdminUserGet());
        commands.put(CommandType.ADMIN_USER_BLOCK_GET, new AdminUserBlockGet());
        commands.put(CommandType.ADMIN_USER_UNBLOCK_GET, new AdminUserUnblockGet());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("get request received");
        processRequest(request, response, "_GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("post request received");
        processRequest(request, response, "_POST");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, String commandSuffix) throws ServletException, IOException {
        String page = commands.get(getCommandType(request, commandSuffix)).execute(request);
        log.info("page \"{}\" was created", page);

        if (page.startsWith("redirect:")) {
            log.info("redirecting");
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            log.info("forwarding");
            request.getRequestDispatcher( TEMPLATES_PATH + page + ".jsp").forward(request, response);
        }
    }

    private CommandType getCommandType(HttpServletRequest request, String commandSuffix) {
        String URI = request.getRequestURI();
        log.info("request URI: {}", URI);

        if (URI.equals("/")) {
            return CommandType.HOME_GET;
        }

        try {
            return CommandType.valueOf(URI
                    .substring(1)
                    .replaceAll("/", "_")
                    .toUpperCase() + commandSuffix);
        } catch (IllegalArgumentException e) {
            return CommandType.ERROR_GET;
        }
    }

}
