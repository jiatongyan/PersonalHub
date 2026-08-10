package com.dylan.personalhub.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Skill {


    private Long id;

    @NotBlank(message = "技能名称不能为空")
    private String name;

    @Min(value = 1, message = "等级最小为1")
    @Max(value = 5, message = "等级最大为5")
    private Integer level;

    private String category;

    private String description;

}