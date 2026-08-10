package com.dylan.personalhub.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;


/**
 * 登录拦截器 + CSRF 防护：
 * - 未登录用户访问 /admin/** 时重定向到登录页
 * - POST/PUT/DELETE 请求校验 CSRF Token
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 排除登录请求
        String path = request.getRequestURI();
        if (path.equals("/admin/login")) {
            return true;
        }

        HttpSession session = request.getSession(false);

        // 检查登录状态
        if (session == null || session.getAttribute("adminUser") == null) {
            response.sendRedirect("/admin/login");
            return false;
        }

        // 确保 session 中有 CSRF Token（首次访问时生成）
        if (session.getAttribute("csrfToken") == null) {
            session.setAttribute("csrfToken", UUID.randomUUID().toString());
        }

        // POST / PUT / DELETE 请求校验 CSRF Token
        String method = request.getMethod();
        if ("POST".equalsIgnoreCase(method)
                || "PUT".equalsIgnoreCase(method)
                || "DELETE".equalsIgnoreCase(method)) {

            String sessionToken = (String) session.getAttribute("csrfToken");
            String requestToken = request.getParameter("_csrf");

            if (sessionToken == null || !sessionToken.equals(requestToken)) {
                response.sendError(403, "CSRF 校验失败");
                return false;
            }
        }

        return true;

    }

}
