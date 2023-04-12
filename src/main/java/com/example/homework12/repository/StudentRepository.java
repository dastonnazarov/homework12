package com.example.homework12.repository;

import com.example.homework12.entity.StudentEntity;
import com.example.homework12.enums.GenderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity,Integer>, PagingAndSortingRepository<StudentEntity,Integer> {
    List<StudentEntity> getByName(String name);

    List<StudentEntity> getBySurname(String surname);

    List<StudentEntity> getByLevel(Integer level);

    List<StudentEntity> getByGender(GenderStatus gender);

    List<StudentEntity> getByCreateDate(LocalDateTime createDate);

    List<StudentEntity> getByCreateDateBetween(LocalDateTime a, LocalDateTime b);

    Optional<StudentEntity> findById(Integer id);

    void deleteById(Integer id);

    Page<StudentEntity> findAll();

    Page<StudentEntity> findAllByName(String name, Pageable pageable);


}

