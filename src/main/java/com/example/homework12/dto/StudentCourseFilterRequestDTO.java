package com.example.homework12.dto;

import com.example.homework12.entity.CourseEntity;
import com.example.homework12.entity.StudentEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentCourseFilterRequestDTO {
private Integer studentId;
private Integer courseId;
private Integer mark;
private Integer fromMark;
private Integer toMark;
private LocalDate fromCreateDate;
private LocalDate toCreateDate;
private String studentName;
private String courseName;
}
