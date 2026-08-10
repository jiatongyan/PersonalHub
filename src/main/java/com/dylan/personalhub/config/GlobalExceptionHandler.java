package com.dylan.personalhub.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;


/**
 * 全局异常处理：避免 Whitelabel Error Page 暴露堆栈信息
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 静态资源 404（如 favicon.ico）直接返回 404，不记日志
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public void handleNoResource() {
        // 不打印堆栈，不返回错误页 — 浏览器默认处理
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception e) {

        log.error("请求异常: {} {}", request.getMethod(), request.getRequestURI(), e);

        ModelAndView mav = new ModelAndView("error/500");

        // 区分是否为 admin 请求
        if (request.getRequestURI().startsWith("/admin")) {
            mav.setViewName("error/admin-500");
        }

        return mav;

    }

}
