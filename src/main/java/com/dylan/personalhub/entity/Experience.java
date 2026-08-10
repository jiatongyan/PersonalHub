package com.dylan.personalhub.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class Experience {

    private Long id;

    @NotBlank(message = "公司名称不能为空")
    private String company;

    private String department;

    @NotBlank(message = "职位不能为空")
    private String position;

    private LocalDate startTime;

    private LocalDate endTime;

    private String description;


}