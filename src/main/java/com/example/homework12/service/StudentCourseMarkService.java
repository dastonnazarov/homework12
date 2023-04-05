package com.example.homework12.service;


import com.example.homework12.dto.StudentCourseMarkDTO;
import com.example.homework12.dto.StudentDTO;
import com.example.homework12.entity.CourseEntity;
import com.example.homework12.entity.StudentCourseMarkEntity;
import com.example.homework12.entity.StudentEntity;
import com.example.homework12.repository.StudentCourseMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository repository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;



    public StudentCourseMarkDTO create(StudentCourseMarkDTO dto) {
        StudentEntity existS = studentService.get(dto.getStudentId());
        CourseEntity existC = courseService.get(dto.getCourseId());

        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setStudentId(existS);
        entity.setCourseId(existC);
        entity.setMark(dto.getMark());
        entity.setCreateDate(dto.getCreateDate());

        repository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public String update(Integer id, StudentCourseMarkDTO dto) {
        StudentEntity studentId = studentService.get(dto.getStudentId());
        CourseEntity courseId = courseService.get(dto.getCourseId());

        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setId(dto.getId());
        entity.setStudentId(studentId);
        entity.setCourseId(courseId);
        entity.setMark(dto.getMark());
        entity.setCreateDate(dto.getCreateDate());
        repository.save(entity);
        return "update successfully";
    }

    public StudentCourseMarkEntity get(Integer id) {
        Optional<StudentCourseMarkEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ArithmeticException("Student not found: " + id);
        }
        return optional.get();
    }
}
