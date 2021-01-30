package com.bezshtanko.university_admission_servlet.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocaleFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LocaleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("request received");
        HttpServletRequest req = (HttpServletRequest) request;

        String locale = req.getParameter("lang");
        if (locale != null) {
            req.getSession().setAttribute("lang", locale);
            log.info("locale has been changed to \"{}\"", locale);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}