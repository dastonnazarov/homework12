package com.example.homework12.service;

import com.example.homework12.dto.CourseDTO;
import com.example.homework12.dto.CourseFilterRequestDTO;
import com.example.homework12.entity.CourseEntity;
import com.example.homework12.repository.CourseCustomRepository;
import com.example.homework12.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseCustomRepository courseCustomRepository;

    public CourseDTO create(CourseDTO courseDTO) {
        CourseEntity entity = new CourseEntity();

        entity.setId(courseDTO.getId());
        entity.setName(courseDTO.getName());
        entity.setPrice(courseDTO.getPrice());
        entity.setDuration(courseDTO.getDuration());
        entity.setCreateDate(courseDTO.getCreateDate());

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
        dto.setCreateDate(entity.getCreateDate());
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
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public boolean update(Integer id, CourseDTO courseDTO) {
        CourseEntity entity = get(id);
        entity.setName(courseDTO.getName());
        entity.setPrice(courseDTO.getPrice());
        entity.setDuration(courseDTO.getDuration());
        entity.setCreateDate(courseDTO.getCreateDate());
        courseRepository.save(entity);
        return true;
    }

    public String delete(Integer id) {
        courseRepository.deleteById(id);
        return "delete success";
    }



    public List<CourseDTO> getByName(String name) {
        List<CourseEntity> entityList =  courseRepository.getByName(name);
        List<CourseDTO> list = new LinkedList<>();
        entityList.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreateDate(entity.getCreateDate());
            list.add(dto);
        });
        return list;
    }

    public List<CourseDTO> getByPrice(Double price) {
        List<CourseEntity> entityList =  courseRepository.getByPrice(price);
        List<CourseDTO> list = new LinkedList<>();
        entityList.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreateDate(entity.getCreateDate());
            list.add(dto);
        });
        return list;
    }

    public List<CourseDTO> getByCreateDate(LocalDateTime localDateTime) {
        List<CourseEntity> entityList =  courseRepository.getByCreateDate(localDateTime);
        List<CourseDTO> list = new LinkedList<>();
        entityList.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreateDate(entity.getCreateDate());
            list.add(dto);
        });
        return list;
    }

    public List<CourseDTO> getByCreateDateBetween(LocalDateTime a, LocalDateTime b) {
        List<CourseEntity> entityList =  courseRepository.getByCreateDateBetween(a,b);
        List<CourseDTO> list = new LinkedList<>();
        entityList.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreateDate(entity.getCreateDate());
            list.add(dto);
        });
        return list;
    }

    public Page<CourseDTO> pagination(int page,int size){
        Sort sort = Sort.by(Sort.Direction.DESC,"createDate");
        Pageable pageable = PageRequest.of(page-1,size,sort);
        Page<CourseEntity> all = courseRepository.findAll(pageable);
        long totalElements = all.getTotalElements();

        List<CourseEntity> entityList = all.getContent();
        List<CourseDTO> dtoList = new LinkedList<>();
        for (CourseEntity entity: entityList){
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        }
        Page<CourseDTO> response = new PageImpl<>(dtoList,pageable,totalElements);
        return response;
    }

    public Page<CourseDTO> paginationPrice(Double price, int page,int size){
        Sort sort = Sort.by(Sort.Direction.DESC,"createDate");
        Pageable pageable = PageRequest.of(page-1,size,sort);
        Page<CourseEntity> all = courseRepository.findAllByPrice(pageable,price);
        long totalElements = all.getTotalElements();

        List<CourseEntity> entityList = all.getContent();
        List<CourseDTO> dtoList = new LinkedList<>();
        for (CourseEntity entity: entityList){
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        }
        Page<CourseDTO> response = new PageImpl<>(dtoList,pageable,totalElements);
        return response;
    }

    public Page<CourseDTO> paginationPrice(Double fromPrice, Double toPrice, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createDate");
        Pageable pageable = PageRequest.of(page-1,size,sort);
        Page<CourseEntity> all = courseRepository.findAllByPriceBetween(fromPrice,toPrice,pageable);
        long totalElements = all.getTotalElements();

        List<CourseEntity> entityList = all.getContent();
        List<CourseDTO> dtoList = new LinkedList<>();
        for (CourseEntity entity: entityList){
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        }
        Page<CourseDTO> response = new PageImpl<>(dtoList,pageable,totalElements);
        return response;
    }

    public List<CourseEntity> test3(){
        List<CourseEntity> list = courseCustomRepository.getAll();
        return list;
    }

    public void filter(CourseFilterRequestDTO courseDTO) {
        List<CourseEntity> courseEntities = courseCustomRepository.filter(courseDTO);
        System.out.println(courseEntities);
    }
}
