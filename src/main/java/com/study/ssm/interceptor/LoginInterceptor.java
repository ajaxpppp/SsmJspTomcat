package com.study.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("loginUser") != null) {
            return true;
        }
        String ctx = request.getContextPath();
        String uri = request.getRequestURI();
        String query = request.getQueryString();
        String redirect = uri + (query != null ? ("?" + query) : "");
        String target = ctx + "/login?needLogin=1&redirect=" + URLEncoder.encode(redirect, StandardCharsets.UTF_8.name());
        response.sendRedirect(target);
        return false;
    }
}
