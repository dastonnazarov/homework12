package com.example.homework12.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
public class StudentCourseD {
    private Integer id;
    private StudentD studentD;
    private CourseD courseD;
    private Integer mark;
    private LocalDateTime createDate;
}
