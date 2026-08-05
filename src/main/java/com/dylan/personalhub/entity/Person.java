package com.dylan.personalhub.entity;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Person {


    private Long id;


    private String name;


    private String title;


    private String description;


    private String about;


    private String label;


    private String email;


    private String github;


    private String avatar;


    private LocalDateTime createTime;

}