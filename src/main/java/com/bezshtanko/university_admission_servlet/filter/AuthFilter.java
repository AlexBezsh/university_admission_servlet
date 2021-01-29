package com.bezshtanko.university_admission_servlet.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class AuthFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        /*HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        HttpSession session = servletRequest.getSession(false);
        if (session == null
                || session.getAttribute("user") == null
                || (((UserDto) session.getAttribute("user")).isAdmin() && !servletRequest.getServletPath().startsWith("/admin"))
                || (((UserDto) session.getAttribute("user")).getRole() == Role.USER && !servletRequest.getServletPath().startsWith("/user"))) {
            request.setAttribute("message", "You have no access to requested page. Please sigh up or log in");
            servletRequest.getServletContext().getRequestDispatcher(servletRequest.getContextPath() + "/").forward(servletRequest, servletResponse);
        }
        chain.doFilter(request, response);*/

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
