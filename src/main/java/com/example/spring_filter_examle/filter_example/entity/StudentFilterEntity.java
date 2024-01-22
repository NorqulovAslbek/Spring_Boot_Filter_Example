package com.example.spring_filter_examle.filter_example.entity;


import com.example.spring_filter_examle.filter_example.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "student_filter")
public class StudentFilterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String level;
    private Integer age;
    private Gender gender;
    private LocalDateTime createdDate;

}
