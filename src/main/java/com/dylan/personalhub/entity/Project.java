package com.dylan.personalhub.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Project {


    private Long id;

    @NotBlank(message = "项目名称不能为空")
    private String name;

    @NotBlank(message = "项目描述不能为空")
    private String description;

    private String techStack;

    private String githubUrl;

    private String image;

    private String category;

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}