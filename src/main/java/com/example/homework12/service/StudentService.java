package com.example.homework12.service;


import com.example.homework12.dto.StudentDTO;
import com.example.homework12.entity.StudentEntity;
import com.example.homework12.enums.GenderStatus;
import com.example.homework12.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;


    public StudentDTO create(StudentDTO studentDTO) {
        StudentEntity entity = new StudentEntity();

        entity.setId(studentDTO.getId());
        entity.setName(studentDTO.getName());
        entity.setSurname(studentDTO.getSurname());
        entity.setLevel(studentDTO.getLevel());
        entity.setAge(studentDTO.getAge());
        entity.setGender(studentDTO.getGender());
        entity.setCreateDate(studentDTO.getCreateDate());

        if (studentDTO.getName() == null || studentDTO.getSurname().isBlank()) {
            throw new RuntimeException("where is name?");
        }

        if (studentDTO.getSurname() == null || studentDTO.getSurname().isBlank()) {
            throw new RuntimeException("where is surname?");
        }
        studentRepository.save(entity);
        return studentDTO;
    }

/*    public List<StudentDTO> getList() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> list = new LinkedList<>();

        iterable.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreateDate(entity.getCreateDate());
            list.add(dto);
        });

        return list;
    }*/

    public StudentDTO getById(Integer id) {
        StudentEntity entity = get(id);
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setLevel(entity.getLevel());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

    public StudentEntity get(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ArithmeticException("Student not found: " + id);
        }
        return optional.get();
    }

    public boolean update(Integer id, StudentDTO studentDTO) {
        StudentEntity entity = get(id);
        entity.setName(studentDTO.getName());
        entity.setSurname(studentDTO.getSurname());
        entity.setLevel(studentDTO.getLevel());
        entity.setAge(studentDTO.getAge());
        entity.setGender(studentDTO.getGender());
        entity.setCreateDate(studentDTO.getCreateDate());
        studentRepository.save(entity);
        return true;
    }

    public boolean delete(Integer id) {
        studentRepository.deleteById(id);
        return true;

    }

    public List<StudentDTO> getByName(String name) {
        List<StudentEntity> entityList = studentRepository.getByName(name);
        List<StudentDTO> list = new LinkedList<>();
        entityList.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreateDate(entity.getCreateDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentDTO> getBySurname(String surname) {
        List<StudentEntity> entityList = studentRepository.getBySurname(surname);
        List<StudentDTO> list = new LinkedList<>();
        entityList.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreateDate(entity.getCreateDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentDTO> getByLevel(Integer level) {
        List<StudentEntity> entityList = studentRepository.getByLevel(level);
        List<StudentDTO> list = new LinkedList<>();
        entityList.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreateDate(entity.getCreateDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentDTO> getByAge(Integer age) {
        List<StudentEntity> entityList = studentRepository.getByLevel(age);
        List<StudentDTO> list = new LinkedList<>();
        entityList.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreateDate(entity.getCreateDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentDTO> getByGender(GenderStatus gender) {
        List<StudentEntity> entityList = studentRepository.getByGender(gender);
        List<StudentDTO> list = new LinkedList<>();
        entityList.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreateDate(entity.getCreateDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentDTO> getByCreateDate(LocalDateTime createDate) {
        List<StudentEntity> entityList = studentRepository.getByCreateDate(createDate);
        List<StudentDTO> list = new LinkedList<>();
        entityList.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreateDate(entity.getCreateDate());
            list.add(dto);
        });
        return list;
    }

    public List<StudentDTO> getBetWeenDate(LocalDateTime a, LocalDateTime b) {
        List<StudentEntity> entityList = studentRepository.getByCreateDateBetween(a, b);
        List<StudentDTO> list = new LinkedList<>();
        entityList.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreateDate(entity.getCreateDate());
            list.add(dto);
        });
        return list;
    }

    public Page<StudentDTO> pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAll();
        Long totalCount = pageObj.getTotalElements();

        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDTO> dtoList = new LinkedList<>();

        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dtoList.add(dto);
        }

        Page<StudentDTO> response = new PageImpl<>(dtoList, pageable, totalCount);
        return response;
    }

    public Page<StudentDTO> PaginationWithName(String name, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAllByName(name, paging);
        long totalCount = pageObj.getTotalElements();
        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDTO> dtoList = new LinkedList<>();

        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dtoList.add(dto);
        }
        Page<StudentDTO> response = new PageImpl<>(dtoList, paging, totalCount);
        return response;
    }

    public Page<StudentDTO> paginationWithLevel(Integer level, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> dtoPage = studentRepository.findAllByLevel(level, pageable);
        long totalElements = dtoPage.getTotalElements();
        List<StudentEntity> entityList = dtoPage.getContent();
        List<StudentDTO> dtoList = new LinkedList<>();

        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        }

        Page<StudentDTO> response = new PageImpl<>(dtoList, pageable, totalElements);
        return response;
    }

    public Page<StudentDTO> paginationWithGender(String gender, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> dtoPage = studentRepository.findAllByGender(gender, pageable);
        long totalElements = dtoPage.getTotalElements();
        List<StudentEntity> entityList = dtoPage.getContent();
        List<StudentDTO> dtoList = new LinkedList<>();

        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        }
        Page<StudentDTO> response = new PageImpl<>(dtoList, pageable, totalElements);
        return response;
    }




}
