package com.bezshtanko.university_admission_servlet.filter;

import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import com.bezshtanko.university_admission_servlet.model.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        HttpSession session = servletRequest.getSession(false);
        if (session == null
                || session.getAttribute("user") == null
                || (((UserDTO) session.getAttribute("user")).hasRole(UserRole.ADMIN) && !servletRequest.getRequestURI().startsWith("/admin"))
                || (((UserDTO) session.getAttribute("user")).hasRole(UserRole.ENTRANT) && !servletRequest.getRequestURI().startsWith("/entrant"))) {
            // Todo request.setAttribute("message", "You have no access to requested page. Please sigh up or log in");
            // servletRequest.getServletContext().getRequestDispatcher(servletRequest.getContextPath() + "/").forward(servletRequest, servletResponse);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
