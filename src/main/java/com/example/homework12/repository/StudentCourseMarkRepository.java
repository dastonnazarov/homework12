package com.example.homework12.repository;

import com.example.homework12.entity.StudentCourseMarkEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity,Integer> {
    List<StudentCourseMarkEntity> getByCreateDate(LocalDateTime createDate);
}
