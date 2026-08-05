package com.dylan.personalhub.entity;

import lombok.Data;
import java.time.LocalDate;


@Data
public class Education {

    private Long id;

    private String school;

    private String major;

    private String degree;

    private LocalDate startTime;

    private LocalDate endTime;

    private String description;


}