package com.example.homework12.service;


import com.example.homework12.dto.StudentCourseMarkDTO;
import com.example.homework12.entity.CourseEntity;
import com.example.homework12.entity.StudentCourseMarkEntity;
import com.example.homework12.entity.StudentEntity;
import com.example.homework12.repository.StudentCourseMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository repository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    public StudentCourseMarkEntity get(Integer id) {
        Optional<StudentCourseMarkEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ArithmeticException("Student not found: " + id);
        }
        return optional.get();
    }


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

        StudentCourseMarkEntity entity = get(id);
        entity.setStudentId(studentId);
        entity.setCourseId(courseId);
        entity.setMark(dto.getMark());
        entity.setCreateDate(dto.getCreateDate());
        repository.save(entity);
        return "update successfully";
    }

    public StudentCourseMarkDTO getById(Integer id) {
        StudentCourseMarkEntity entity = get(id);
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();

        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudentId().getId());
        dto.setCourseId(entity.getCourseId().getId());
        dto.setMark(entity.getMark());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

    public List<StudentCourseMarkDTO> getAll() {
        Iterable<StudentCourseMarkEntity> all = repository.findAll();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        all.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudentId().getId());
            dto.setCourseId(entity.getCourseId().getId());
            dto.setMark(entity.getMark());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        });
        return dtoList;
    }


}
