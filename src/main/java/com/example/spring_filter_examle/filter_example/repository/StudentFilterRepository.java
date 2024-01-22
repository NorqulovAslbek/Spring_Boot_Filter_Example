package com.example.spring_filter_examle.filter_example.repository;


import com.example.spring_filter_examle.filter_example.dto.PaginationResultDTO;
import com.example.spring_filter_examle.filter_example.dto.StudentFilterDTO;
import com.example.spring_filter_examle.filter_example.entity.StudentFilterEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class StudentFilterRepository {
    @Autowired
    private EntityManager entityManager;

    public PaginationResultDTO<StudentFilterEntity> filter(StudentFilterDTO filter, Integer page, Integer size) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (filter.getId() != null) {
            builder.append(" and s.id=:id ");
            params.put("id", filter.getId());
        }
        if (filter.getAge() != null) {
            builder.append(" and age=:age ");
            params.put("age", filter.getAge());
        }
        if (filter.getLevel() != null) {
            builder.append(" and level=:level ");
            params.put("level", filter.getLevel());
        }
        if (filter.getName() != null) {
            builder.append(" and lower(name) like :name ");
            params.put("name", "%" + filter.getName().toLowerCase() + "%");
        }
        if (filter.getSurname() != null) {
            builder.append(" and lower(surname) like :surname ");
            params.put("surname", "%" + filter.getSurname() + "%");
        }

        if (filter.getFromDate() != null && filter.getToDate() != null) {
            LocalDateTime fromDate = LocalDateTime.of(filter.getFromDate(), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(filter.getToDate(), LocalTime.MAX);
            builder.append(" and createdDate between :fromDate and :toDate ");
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
        }
        if (filter.getFromDate() != null) {
            LocalDateTime fromDate = LocalDateTime.of(filter.getFromDate(), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(filter.getFromDate(), LocalTime.MAX);
            builder.append(" and createdDate between : fromDate and toDate ");
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
        }
        if (filter.getToDate() != null) {
            LocalDateTime time = LocalDateTime.of(filter.getToDate(), LocalTime.MAX);
            builder.append("and createdDate <= :time ");
            params.put("time", time);
        }

        String selectBuilder = "From StudentFilterEntity s where 1=1 " + builder + " order by createdDate desc";

        String countBuilder = "Select count(s) from StudentFilterEntity as s where 1=1 " + builder;

        Query selectQuery = entityManager.createQuery(selectBuilder);
        Query countQuery = entityManager.createQuery(countBuilder);

        selectQuery.setMaxResults(size);
        selectQuery.setFirstResult((page - 1) * size);

        for (Map.Entry<String, Object> param : params.entrySet()) {
            selectQuery.setParameter(param.getKey(), param.getValue());
            countQuery.setParameter(param.getKey(), param.getValue());
        }
        List<StudentFilterEntity> entityList = selectQuery.getResultList();
        Long totalElements = (Long) countQuery.getSingleResult();

        return new PaginationResultDTO<>(entityList, totalElements);

    }


}
