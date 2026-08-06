package com.dylan.personalhub.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Article {

    private Long id;

    private String title;

    private String summary;

    private String content;

    private String category;

    private String cover;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}