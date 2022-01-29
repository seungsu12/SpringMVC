package com.spring.mvc.web.session;


import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private final String SESSION_COOKIE_NAME ="mySessionId";
    private Map<String,Object> sessionStore = new ConcurrentHashMap<>();


    public void createSession(Object value,HttpServletResponse response) {

        String sessionId = UUID.randomUUID().toString();

        sessionStore.put(sessionId,value);

        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionCookie);


    }

    public Object getSession(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request,SESSION_COOKIE_NAME);
        if (sessionCookie == null) {
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());

    }

    public Cookie findCookie(HttpServletRequest request,String sessionCookieName) {

        if (request.getCookies() == null) {
            return null;
        }
        return Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals(sessionCookieName))
                .findFirst()
                .orElse(null);

    }

    public void expireSession(HttpServletRequest request) {

        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);

        if (sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }
}
