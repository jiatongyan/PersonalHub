package com.dylan.personalhub.controller.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/admin/upload")
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    private static final String UPLOAD_PATH = System.getProperty("user.dir") + "/uploads/images/";

    @PostMapping("/image")
    public String upload(@RequestParam("file") MultipartFile file)
            throws IOException {

        log.info("收到上传请求: fileName={}, size={} bytes",
                file.getOriginalFilename(), file.getSize());

        // 创建目录
        File directory = new File(UPLOAD_PATH);

        if (!directory.exists()) {
            directory.mkdirs();
            log.info("创建上传目录: {}", directory.getAbsolutePath());
        }

        // 文件名
        String filename =
                UUID.randomUUID() + "_" + file.getOriginalFilename();
        File target = new File(UPLOAD_PATH + filename);


        // 保存文件
        file.transferTo(target);

        log.info("文件上传成功: {} -> {}", file.getOriginalFilename(),
                target.getAbsolutePath());

        // 返回图片访问地址
        return "/uploads/images/" + filename;

    }

}