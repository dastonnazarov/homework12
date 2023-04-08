package com.example.homework12.repository;

import com.example.homework12.dto.StudentCourseMarkDTO;
import com.example.homework12.entity.StudentCourseMarkEntity;
import com.example.homework12.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity,Integer> {
    List<StudentCourseMarkEntity> getByCreateDate(LocalDateTime createDate);
    List<StudentCourseMarkEntity> getByCreateDateBetween(LocalDateTime firstDate, LocalDateTime secondDate);
//    Iterable<StudentCourseMarkEntity> findAllByStudentIdOrderByCreateDateDesc(Integer id);
    List<StudentCourseMarkEntity>findAllByStudentIdAndMarkOrderByCreateDateDesc(Integer id,Integer mark);
}
