package com.dylan.personalhub.entity;

import lombok.Data;

@Data
public class Skill {


    private Long id;

    private String name;

    private Integer level;

    private String category;

    private String description;

}