package com.dylan.personalhub.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;

    @Value("${app.upload.path}")
    private String uploadPath;

    public WebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String basePath = uploadPath;

        // 静态资源映射到 upload 根目录（因为 URL /uploads/images/xxx 需要从 /uploads/ 开始）
        File uploadDir = new File(basePath).getParentFile();
        String location = "file:" + uploadDir.getAbsolutePath() + File.separator;

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(location);

    }

}