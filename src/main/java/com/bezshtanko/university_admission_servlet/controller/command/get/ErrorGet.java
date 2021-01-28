package com.bezshtanko.university_admission_servlet.controller.command.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorGet implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String templatePath, String templateSuffix) throws ServletException, IOException {
        request.getRequestDispatcher(templatePath + "error" + templateSuffix).forward(request, response);
    }
}
