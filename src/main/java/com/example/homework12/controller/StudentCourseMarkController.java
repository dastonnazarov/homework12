package com.example.homework12.controller;

import com.example.homework12.dto.StudentCourseMarkDTO;
import com.example.homework12.dto.StudentDTO;
import com.example.homework12.service.StudentCourseMarkService;
import org.aspectj.lang.annotation.AdviceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("studentCourseMark")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService studentCourseMarkService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseMarkDTO dto) {
        StudentCourseMarkDTO list = studentCourseMarkService.create(dto);
        return ResponseEntity.ok(list);
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentCourseMarkDTO dto) {
        return ResponseEntity.ok(studentCourseMarkService.update(id,dto));
    }
}
