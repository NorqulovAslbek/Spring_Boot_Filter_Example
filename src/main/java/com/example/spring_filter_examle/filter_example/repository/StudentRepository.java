package com.example.spring_filter_examle.filter_example.repository;

import com.example.spring_filter_examle.filter_example.entity.StudentFilterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentFilterEntity,Integer> {

}
