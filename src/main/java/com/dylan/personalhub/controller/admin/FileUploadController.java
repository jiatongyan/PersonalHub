package com.dylan.personalhub.controller.admin;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/admin/upload")
public class FileUploadController {

    private static final String UPLOAD_PATH = System.getProperty("user.dir") + "/uploads/images/";

    @PostMapping("/image")


    public String upload(@RequestParam("file") MultipartFile file)
            throws IOException {

        // 创建目录
        File directory = new File(UPLOAD_PATH);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 文件名
        String filename =
                UUID.randomUUID() + "_" + file.getOriginalFilename();
        File target = new File(UPLOAD_PATH + filename);


        // 保存文件
        file.transferTo(target);

        // 返回 Markdown 图片地址
        return "/uploads/images/" + filename;

    }

}