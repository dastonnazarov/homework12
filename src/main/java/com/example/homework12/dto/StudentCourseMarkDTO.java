package com.example.homework12.dto;


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
