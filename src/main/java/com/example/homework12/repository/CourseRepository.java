package com.example.homework12.repository;


import com.example.homework12.dto.CourseDTO;
import com.example.homework12.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity,Integer>, PagingAndSortingRepository<CourseEntity,Integer> {
    List<CourseEntity> getByName(String name);
    List<CourseEntity> getByPrice(Double price);
    List<CourseEntity> getByCreateDate(LocalDateTime localDateTime);
    List<CourseEntity> getByCreateDateBetween(LocalDateTime a, LocalDateTime b);

    Page<CourseEntity> findAllByPrice(Pageable pageable, Double price);
    Page<CourseEntity> findAllByPriceBetween(Double fromPrice, Double toPrice, Pageable pageable);
}
