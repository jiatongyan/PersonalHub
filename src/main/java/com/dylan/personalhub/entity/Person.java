package com.dylan.personalhub.entity;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Person {


    private Long id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "头衔不能为空")
    private String title;

    private String description;

    @Size(max = 1000, message = "关于介绍不能超过1000字")
    private String about;

    private String label;

    @Email(message = "邮箱格式不正确")
    private String email;

    private String github;

    private String avatar;

    private LocalDateTime createTime;

}