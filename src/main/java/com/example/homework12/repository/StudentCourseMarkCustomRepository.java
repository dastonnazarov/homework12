package com.example.homework12.repository;

import com.example.homework12.dto.StudentCourseFilterRequestDTO;
import com.example.homework12.entity.StudentCourseMarkEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentCourseMarkCustomRepository {
    @Autowired
    private EntityManager entityManager;

    //studentId,courseId,markFrom, markTo,createdDateFrom,createdDateTo,
    //            studentName, courseName

    public List<StudentCourseMarkEntity> filter(StudentCourseFilterRequestDTO filterDTO) {
        Map<String, Object> map = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append("select sc from StudentCourseMarkEntity as sc where id=id");

        if (filterDTO.getStudentId() != 0) {
            builder.append(" and sc.student=:student");
            map.put("student", filterDTO.getStudentId());
        }

        if (filterDTO.getCourseId() != 0) {
            builder.append(" and sc.courseId=:courseId");
            map.put("courseId", filterDTO.getCourseId());
        }

        if (filterDTO.getFromMark() != 0 && filterDTO.getToMark() != 0) {
            builder.append(" and sc.mark between: fromMark and :toMark");
            map.put("fromMark", filterDTO.getFromMark());
            map.put("toMark", filterDTO.getToMark());
        } else if (filterDTO.getFromMark() != null) {
            builder.append("and sc.mark>=:fromMark");
            map.put("fromMark", filterDTO.getFromMark());
        } else if (filterDTO.getToMark() != 0) {
            builder.append("and sc.mark<=:toMark");
            map.put("toMark", filterDTO.getToMark());
        }


        if (filterDTO.getFromCreateDate() != null && filterDTO.getToCreateDate() != null) {
            builder.append(" and sc.createDate between: fromCreateDate and :toCreatDae");
            map.put(" fromCreateDate", LocalDateTime.of(filterDTO.getFromCreateDate(), LocalTime.MIN));
            map.put(" toCreatDae", LocalDateTime.of(filterDTO.getToCreateDate(), LocalTime.MAX));
        } else if (filterDTO.getFromCreateDate() != null) {
            builder.append(" and sc.createDate>=:fromCreateDate");
            map.put(" fromCreateDate", filterDTO.getFromCreateDate());
        } else if (filterDTO.getToCreateDate() != null) {
            builder.append(" and sc.createDate<=:toCreatDae");
            map.put(" toCreatDae", filterDTO.getToCreateDate());
        }

        if (filterDTO.getStudentName() != null) {
            builder.append(" and sc.name like =:name");
            map.put("name", "%" + filterDTO.getStudentName() + "%");
        }
        if (filterDTO.getCourseName() != null) {
            builder.append(" and sc.name like =:name");
            map.put("name", "%" + filterDTO.getCourseName() + "%");
        }



        Query query = this.entityManager.createQuery(builder.toString());
        for (Map.Entry<String, Object> param : map.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List list = query.getResultList();
        return list;
    }
}
