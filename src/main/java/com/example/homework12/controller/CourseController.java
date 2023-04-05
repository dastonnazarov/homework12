package com.example.homework12.controller;

import com.example.homework12.dto.CourseDTO;
import com.example.homework12.dto.StudentDTO;
import com.example.homework12.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO courseDTO) {
        CourseDTO dto = courseService.create(courseDTO);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        CourseDTO dto = courseService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getList")
    public ResponseEntity<?> getList() {
        List<CourseDTO> dto = courseService.getList();
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Integer id,@RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.update(id,courseDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Integer id) {
        return ResponseEntity.ok(courseService.delete(id));
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        List<CourseDTO> list = courseService.getByName(name);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByPrice/{price}")
    public ResponseEntity<?> getByPrice(@PathVariable("price") Double price) {
        List<CourseDTO> list = courseService.getByPrice(price);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getCreateDate/{createDate}")
    public ResponseEntity<?> getByCreateDate(@PathVariable("createDate")LocalDateTime localDateTime){
        return ResponseEntity.ok(courseService.getByCreateDate(localDateTime));
    }

    @GetMapping("/getByCreateDateBetween")
    public ResponseEntity<?> getByCreateDateBetween(@RequestParam("fromDate") LocalDateTime a,@RequestParam("toDate") LocalDateTime b){
        return ResponseEntity.ok(courseService.getByCreateDateBetween(a,b));
    }
}
