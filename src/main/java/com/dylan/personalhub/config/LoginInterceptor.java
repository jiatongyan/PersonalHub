package com.dylan.personalhub.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * 登录拦截器：未登录用户访问 /admin/** 时重定向到登录页
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("adminUser") != null) {
            // 已登录，放行
            return true;
        }

        // 未登录，重定向到登录页
        response.sendRedirect("/admin/login");
        return false;

    }

}
