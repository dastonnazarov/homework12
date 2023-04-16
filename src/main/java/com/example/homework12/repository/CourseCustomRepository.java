package com.example.homework12.repository;

import com.example.homework12.dto.CourseFilterRequestDTO;
import com.example.homework12.entity.CourseEntity;
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
public class CourseCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public List<CourseEntity> getAll() {
        Query query = this.entityManager.createQuery("select c  from  CourseEntity as c ");
        List resultList = query.getResultList();
        return resultList;
    }


//    public List<CourseEntity> filter(CourseFilterRequestDTO filterDTO) {
//        Map<String,Object> params = new HashMap<>();
//        StringBuilder builder = new StringBuilder();
//        builder.append("select c from CourseEntity as c where id = id");
//
//        if(filterDTO.getName()!=null){
//            builder.append("and c.name = :name");
//            params.put("name",filterDTO.getName());
//        }
//
//        if(filterDTO.getPrice()!=0){
//            builder.append("and c.price = :price");
//            params.put("price",filterDTO.getName());
//        }
//
//        if(filterDTO.getDuration()!=null){
//            builder.append("and c.duration = :duration");
//            params.put("duration",filterDTO.getDuration());
//        }
//
//
//
//        if(filterDTO.getFromDate()!=null && filterDTO.getToDate()!=null){
//            builder.append("and c.createDate between: fromDate and  :toDate");
//            params.put("fromDate", LocalDateTime.of(filterDTO.getFromDate(), LocalTime.MIN));
//            params.put("toDate", LocalDateTime.of(filterDTO.getToDate(),LocalTime.MAX));
//        }
//        if(filterDTO.getFromDate()!=null){
//            builder.append("fromDate c.createDate>=:fromDate");
//            params.put("fromDate",LocalDateTime.of(filterDTO.getFromDate(),LocalTime.MIN));
//        }
//
//        if(filterDTO.getFromDate()!=null){
//            builder.append("toDate c.createDate<=:toDate");
//            params.put("toDate",LocalDateTime.of(filterDTO.getToDate(),LocalTime.MIN));
//        }
//
//        Query query = this.entityManager.createQuery(builder.toString());
//        for (Map.Entry<String,Object> param: params.entrySet()){
//            query.setParameter(param.getKey(),param.getValue());
//        }
//
//        List  list = query.getResultList();
//        return list;
//    }


    public List<CourseEntity> filter(CourseFilterRequestDTO filterDTO) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append("Select s From StudentEntity as s where id = id");

        if (filterDTO.getName() != null) {
            builder.append(" and s.name = :name");
            params.put("name", filterDTO.getName());
        }
        if (filterDTO.getPrice() != null) {
            builder.append(" and s.price = :price");
            params.put("price", filterDTO.getPrice());
        }

        if (filterDTO.getDuration()!= null) {
            builder.append(" and s.duration = :duration");
            params.put("level", filterDTO.getDuration());
        }


        if (filterDTO.getFromDate() != null && filterDTO.getToDate() != null) {
            builder.append(" and s.createDate between :fromDate and :fromDate ");
            params.put("fromDate", LocalDateTime.of(filterDTO.getFromDate(), LocalTime.MIN));
            params.put("toDate", LocalDateTime.of(filterDTO.getToDate(), LocalTime.MAX));

        } else if (filterDTO.getFromDate() != null) {
            builder.append(" and s.createDate >= :fromDate ");
            params.put("fromDate", LocalDateTime.of(filterDTO.getFromDate(), LocalTime.MIN));

        } else if (filterDTO.getToDate() != null) {
            builder.append(" and s.createDate <= :toDate ");
            params.put("toDate", LocalDateTime.of(filterDTO.getToDate(), LocalTime.MIN));

        }

        Query query = this.entityManager.createQuery(builder.toString());
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List  list = query.getResultList();
        return list;
    }
}
