package com.example.homework12.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "student_course_mark")
public class StudentCourseMarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_Id")
    private StudentEntity studentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_Id")
    private CourseEntity courseId;

    @Column(name = "mark")
    private Integer mark;

    @Column(name = "createDate")
    private LocalDateTime createDate;



}
