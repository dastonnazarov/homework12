package com.example.homework12.dto;

import com.example.homework12.entity.CourseEntity;
import com.example.homework12.entity.StudentEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StudentCourseMarkDTO {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Integer mark;
    private LocalDateTime createDate = LocalDateTime.now();

}
