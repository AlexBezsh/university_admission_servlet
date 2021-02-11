package com.bezshtanko.university_admission_servlet.controller.command.admin.get;

import com.bezshtanko.university_admission_servlet.controller.command.Command;
import com.bezshtanko.university_admission_servlet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminUserBlockGet implements Command {

    private static final Logger log = LoggerFactory.getLogger(AdminUserBlockGet.class);

    private final UserService userService;

    public AdminUserBlockGet(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Executing admin user block get command");
        Long userId = Long.parseLong(request.getParameter("userId"));
        userService.blockUser(userId);
        return "redirect:/admin/entrant?userId=" + userId;
    }
}
