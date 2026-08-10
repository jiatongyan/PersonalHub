package com.dylan.personalhub.controller.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/admin/upload")
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @Value("${app.upload.path}")
    private String uploadPath;

    @PostMapping("/image")
    public String upload(@RequestParam("file") MultipartFile file)
            throws IOException {

        log.info("收到上传请求: fileName={}, size={} bytes",
                file.getOriginalFilename(), file.getSize());

        // 路径：使用配置的绝对路径
        String basePath = uploadPath;
        if (!basePath.endsWith("/") && !basePath.endsWith("\\")) {
            basePath += File.separator;
        }

        // 创建目录
        File directory = new File(basePath);

        if (!directory.exists()) {
            directory.mkdirs();
            log.info("创建上传目录: {}", directory.getAbsolutePath());
        }

        // 文件名（去除路径遍历风险）
        String originalName = file.getOriginalFilename();
        String safeName = (originalName != null)
                ? originalName.replaceAll("[\\\\/]", "_")
                : "unknown";
        String filename = UUID.randomUUID() + "_" + safeName;
        File target = new File(basePath + filename);


        // 保存文件
        file.transferTo(target);

        log.info("文件上传成功: {} -> {}", file.getOriginalFilename(),
                target.getAbsolutePath());

        // 返回图片访问地址
        return "/uploads/images/" + filename;

    }

}