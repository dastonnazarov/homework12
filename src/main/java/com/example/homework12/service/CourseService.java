package com.example.homework12.service;

import com.example.homework12.dto.CourseDTO;
import com.example.homework12.dto.StudentDTO;
import com.example.homework12.entity.CourseEntity;
import com.example.homework12.entity.StudentEntity;
import com.example.homework12.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseDTO create(CourseDTO courseDTO) {
        CourseEntity entity = new CourseEntity();

        entity.setId(courseDTO.getId());
        entity.setName(courseDTO.getName());
        entity.setPrice(courseDTO.getPrice());
        entity.setDuration(courseDTO.getDuration());
        entity.setCreatedDate(courseDTO.getCreatedDate());

        if (courseDTO.getName() == null || courseDTO.getName().isBlank()) {
            throw new RuntimeException("course name doesn't find");
        }
        courseRepository.save(entity);
        return courseDTO;
    }

    public CourseEntity get(Integer id) {
        Optional<CourseEntity> byId = courseRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ArithmeticException("course not found " + id);
        }
        return byId.get();
    }

    public CourseDTO getById(Integer id) {
        CourseEntity entity = get(id);
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public List<CourseDTO> getList() {
        Iterable<CourseEntity> all = courseRepository.findAll();
        List<CourseDTO> dtoList = new LinkedList<>();

        all.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public boolean update(Integer id, CourseDTO courseDTO) {
        CourseEntity entity = get(id);
        entity.setName(courseDTO.getName());
        entity.setPrice(courseDTO.getPrice());
        entity.setDuration(courseDTO.getDuration());
        entity.setCreatedDate(courseDTO.getCreatedDate());
        courseRepository.save(entity);
        return true;
    }

    public String delete(Integer id) {
        courseRepository.deleteById(id);
        return "delete success";
    }

//    public List<CourseDTO> getByName(String name) {
//
//        List<CourseDTO> dtoList = new LinkedList<>();
//        List<CourseEntity> entityList = courseRepository.getByName(name);
//
//        entityList.forEach(entity ->{
//            CourseDTO dto = new CourseDTO();
//            dto.setId(entity.getId());
//            dto.setName(entity.getName());
//            dto.setPrice(entity.getPrice());
//            dto.setDuration(entity.getDuration());
//            dto.setCreatedDate(entity.getCreatedDate());
//            dtoList.add(dto);
//        });
//        return dtoList;
//    }

    public List<CourseDTO> getByName(String name) {
        List<CourseDTO> entityList =  courseRepository.getByName(name);
        List<CourseDTO> list = new LinkedList<>();
        entityList.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

}