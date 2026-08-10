package com.dylan.personalhub.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Article {

    private Long id;

    @NotBlank(message = "标题不能为空")
    @Size(max = 200, message = "标题不能超过200字")
    private String title;

    @Size(max = 500, message = "摘要不能超过500字")
    private String summary;

    @NotBlank(message = "内容不能为空")
    private String content;

    private String category;

    private String cover;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}