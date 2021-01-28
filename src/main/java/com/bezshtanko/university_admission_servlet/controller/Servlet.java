package com.bezshtanko.university_admission_servlet.controller;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.controller.command.CommandType;
import com.bezshtanko.university_admission_servlet.controller.command.get.ErrorGet;
import com.bezshtanko.university_admission_servlet.controller.command.get.HomeGet;
import com.bezshtanko.university_admission_servlet.controller.command.get.LoginGet;
import com.bezshtanko.university_admission_servlet.controller.command.get.RegisterGet;
import com.bezshtanko.university_admission_servlet.controller.command.post.LoginPost;
import com.bezshtanko.university_admission_servlet.controller.command.post.RegisterPost;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {

    private final Map<CommandType, Command> commands = new HashMap<>();

    @Override
    public void init() {
        commands.put(CommandType.HOME_GET, new HomeGet());
        commands.put(CommandType.LOGIN_GET, new LoginGet());
        commands.put(CommandType.LOGIN_POST, new LoginPost());
        commands.put(CommandType.REGISTER_GET, new RegisterGet());
        commands.put(CommandType.REGISTER_POST, new RegisterPost());
        commands.put(CommandType.ERROR_GET, new ErrorGet());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "_GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "_POST");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, String commandSuffix) throws ServletException, IOException {
        commands.get(getCommandType(request, commandSuffix))
                .execute(request, response, "WEB-INF/jsp/", ".jsp");
    }

    private CommandType getCommandType(HttpServletRequest request, String commandSuffix) {
        String path = request.getRequestURI();
        if (path.equals("/")) {
            return CommandType.HOME_GET;
        }

        try {
            return CommandType.valueOf(path
                    .substring(1)
                    .replaceAll("/", "_")
                    .toUpperCase() + commandSuffix);
        } catch (IllegalArgumentException e) {
            return CommandType.ERROR_GET;
        }
    }

}
