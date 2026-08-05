package com.dylan.personalhub.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Experience {

    private Long id;

    private String company;

    private String department;

    private String position;

    private LocalDate startTime;

    private LocalDate endTime;

    private String description;


}