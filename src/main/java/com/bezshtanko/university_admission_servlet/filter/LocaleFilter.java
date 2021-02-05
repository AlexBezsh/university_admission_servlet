package com.bezshtanko.university_admission_servlet.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocaleFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LocaleFilter.class);

    public static final String LANG_ATTRIBUTE_NAME = "lang";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Request received");
        HttpServletRequest req = (HttpServletRequest) request;

        String locale = req.getParameter(LANG_ATTRIBUTE_NAME);
        if (locale != null) {
            req.getSession().setAttribute(LANG_ATTRIBUTE_NAME, locale);
            log.info("Locale has been changed to \"{}\"", locale);
        } else if (req.getSession().getAttribute(LANG_ATTRIBUTE_NAME) == null) {
            req.getSession().setAttribute(LANG_ATTRIBUTE_NAME, "en");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
