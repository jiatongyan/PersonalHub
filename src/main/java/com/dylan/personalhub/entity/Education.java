package com.dylan.personalhub.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;


@Data
public class Education {

    private Long id;

    @NotBlank(message = "学校不能为空")
    private String school;

    @NotBlank(message = "专业不能为空")
    private String major;

    @NotBlank(message = "学历不能为空")
    private String degree;

    private LocalDate startTime;

    private LocalDate endTime;

    private String description;


}