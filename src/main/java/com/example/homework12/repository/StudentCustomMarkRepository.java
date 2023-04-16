package com.example.homework12.repository;

import com.example.homework12.dto.StudentFilterRequestDTO;
import com.example.homework12.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentCustomMarkRepository {
    @Autowired
    private EntityManager entityManager;

    public List<StudentEntity> getAll() {
        Query query = this.entityManager.createQuery("select s  from  StudentEntity as s ");
        List resultList = query.getResultList();
        return resultList;
    }

    public List<StudentEntity> filter(StudentFilterRequestDTO filterDTO) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append("Select s From StudentEntity as s where id = id");

        if (filterDTO.getName() != null) {
            builder.append(" and s.name = :name");
            params.put("name", filterDTO.getName());
        }
        if (filterDTO.getSurname() != null) {
            builder.append(" and s.surname = :surname");
            params.put("surname", filterDTO.getSurname());
        }

        if (filterDTO.getLevel()!= null) {
            builder.append(" and s.level = :level");
            params.put("level", filterDTO.getLevel());
        }
        if (filterDTO.getLevel()!= null) {
            builder.append(" and s.age = :age");
            params.put("age", filterDTO.getAge());
        }

        if (filterDTO.getLevel()!= null) {
            builder.append(" and s.gender = :gender");
            params.put("gender", filterDTO.getGender());
        }

        if (filterDTO.getDateFrom() != null && filterDTO.getDateTo() != null) {
            builder.append(" and s.createDate between :fromDate and :fromDate ");
            params.put("fromDate", LocalDateTime.of(filterDTO.getDateFrom(), LocalTime.MIN));
            params.put("toDate", LocalDateTime.of(filterDTO.getDateTo(), LocalTime.MAX));

        } else if (filterDTO.getDateFrom() != null) {
            builder.append(" and s.createDate >= :fromDate ");
            params.put("fromDate", LocalDateTime.of(filterDTO.getDateFrom(), LocalTime.MIN));

        } else if (filterDTO.getDateTo() != null) {
            builder.append(" and s.createDate <= :toDate ");
            params.put("toDate", LocalDateTime.of(filterDTO.getDateTo(), LocalTime.MAX));
        }

        Query query = this.entityManager.createQuery(builder.toString());
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List studentList = query.getResultList();
        return studentList;
    }
}
