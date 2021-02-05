package com.bezshtanko.university_admission_servlet.filter;

import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.model.user.UserRole;
import com.bezshtanko.university_admission_servlet.service.Services;
import com.bezshtanko.university_admission_servlet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    public static final String USER_SESSION_ATTRIBUTE_NAME = "user";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Request received");
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        servletResponse.setHeader("Pragma", "no-cache");
        servletResponse.setDateHeader("Expires", 0);

        String requestURI = servletRequest.getRequestURI();
        HttpSession session = servletRequest.getSession(false);
        UserDTO user = null;

        if (session != null && (user = (UserDTO) session.getAttribute(USER_SESSION_ATTRIBUTE_NAME)) != null) {
            if (!requestURI.equals("/logout")) {
                UserService userService = (UserService) Services.USER_SERVICE.get();
                UserDTO auth = userService.getByEmail(user.getEmail());
                auth.setEnrollments(user.getEnrollments());
                session.setAttribute(USER_SESSION_ATTRIBUTE_NAME, user = auth);

                if (user.hasRole(UserRole.ADMIN) && !requestURI.startsWith("/admin")) {
                    log.error("User {} has no access to page \"{}\"", user, requestURI);
                    servletResponse.sendRedirect("/admin/faculties");
                    return;
                }
                if (user.hasRole(UserRole.ENTRANT) && !requestURI.startsWith("/entrant")) {
                    log.error("User {} has no access to page \"{}\"", user, requestURI);
                    servletResponse.sendRedirect("/entrant/faculties");
                    return;
                }
                if (user.getRoles().isEmpty() || user.isBlocked()) {
                    log.error("User {} is blocked or has no roles. Access denied", user);
                    servletResponse.sendRedirect("/logout");
                    return;
                }
            }
        } else {
            if (requestURI.startsWith("/entrant") || requestURI.startsWith("/admin") || requestURI.equals("/logout")) {
                log.error("User {} has no access to page \"{}\"", user, requestURI);
                servletResponse.sendRedirect("/");
                return;
            }
        }
        log.info("User {} has access to page \"{}\"", user, requestURI);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
