package com.example.homework12.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CourseDTO {
   private Integer id;
   private String name;
   private Double price;
   private String duration;
   private LocalDate fromDate;
   private LocalDate toDate;
   private LocalDateTime createDate = LocalDateTime.now();
}
