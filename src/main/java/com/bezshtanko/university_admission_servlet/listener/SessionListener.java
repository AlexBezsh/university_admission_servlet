package com.bezshtanko.university_admission_servlet.listener;

import com.bezshtanko.university_admission_servlet.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Set;

public class SessionListener implements HttpSessionListener {

    private static final Logger log = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        Set<String> loggedUsers = (Set<String>) session
                .getServletContext()
                .getAttribute("loggedUsers");
        loggedUsers.remove(((UserDTO) session.getAttribute("user")).getEmail());
        log.info("Session was destroyed");
    }
}
