package com.example.spring_filter_examle.filter_example.dto;

import com.example.spring_filter_examle.filter_example.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentFilterDTO {
    private Integer id;
    private String name;
    private String surname;
    private String level;
    private Integer age;
    private Gender gender;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDateTime createdDate;
}
