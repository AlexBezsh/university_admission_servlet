package com.bezshtanko.university_admission_servlet.controller.command.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterGet implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String templatesPath, String templatesSuffix) throws ServletException, IOException {
        request.setAttribute("user", new User());
        request.getRequestDispatcher(templatesPath + "reg_form" + templatesSuffix).forward(request, response);
    }
}
