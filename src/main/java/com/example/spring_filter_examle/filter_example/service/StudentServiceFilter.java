package com.example.spring_filter_examle.filter_example.service;


import com.example.spring_filter_examle.filter_example.dto.PaginationResultDTO;
import com.example.spring_filter_examle.filter_example.dto.StudentFilterDTO;
import com.example.spring_filter_examle.filter_example.entity.StudentFilterEntity;
import com.example.spring_filter_examle.filter_example.repository.StudentFilterRepository;
import com.example.spring_filter_examle.filter_example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentServiceFilter {
    @Autowired
    private StudentFilterRepository repository;
    @Autowired
    private StudentRepository studentRepository;

    public StudentFilterDTO create(StudentFilterDTO dto) {
        dto.setCreatedDate(LocalDateTime.now());
        StudentFilterEntity studentEntity = toEntity(dto);
        StudentFilterEntity save = studentRepository.save(studentEntity);
        return getEntityToDTO(save);
    }

    public PageImpl<StudentFilterDTO> filter(StudentFilterDTO dto, Integer page, Integer size) {
        PaginationResultDTO<StudentFilterEntity> filter = repository.filter(dto, page, size);

        List<StudentFilterDTO> list = new LinkedList<>();

        for (StudentFilterEntity studentEntity : filter.getList()) {
            list.add(getEntityToDTO(studentEntity));
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "createdDate");
        return new PageImpl<>(list, pageable, filter.getTotalSize());
    }

    /**
     * bu method bizga  StudentEntity entity type dagi parametr qabul qilib uni StudentDTO ga o'zgartirib beradi.
     *
     * @param entity -StudentEntity
     * @return StudentDTO
     */
    public StudentFilterDTO getEntityToDTO(StudentFilterEntity entity) {
        StudentFilterDTO dto = new StudentFilterDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setGender(entity.getGender());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public StudentFilterEntity toEntity(StudentFilterDTO dto) {
        StudentFilterEntity entity = new StudentFilterEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setLevel(dto.getLevel());
        entity.setGender(dto.getGender());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }

}
