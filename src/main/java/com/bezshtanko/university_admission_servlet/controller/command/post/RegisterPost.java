package com.bezshtanko.university_admission_servlet.controller.command.post;

import com.bezshtanko.university_admission_servlet.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterPost implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String templatesPath, String templatesSuffix) {
    }
}
