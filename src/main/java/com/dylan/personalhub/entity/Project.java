package com.dylan.personalhub.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Project {


    private Long id;

    private String name;

    private String description;

    private String techStack;

    private String githubUrl;

    private String image;

    private String category;

    private String status;

    private LocalDateTime createTime;


}