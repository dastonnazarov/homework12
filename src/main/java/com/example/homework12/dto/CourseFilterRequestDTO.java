package com.example.homework12.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseFilterRequestDTO {
    private Integer id;
    private String name;
    private Double price;
    private String duration;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Double fromPrice;
    private Double toPrice;
}
