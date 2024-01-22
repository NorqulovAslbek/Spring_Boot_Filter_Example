package com.example.spring_filter_examle.filter_example.controller;


import com.example.spring_filter_examle.filter_example.dto.StudentFilterDTO;
import com.example.spring_filter_examle.filter_example.entity.StudentFilterEntity;
import com.example.spring_filter_examle.filter_example.service.StudentServiceFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/filterStudent")
@RestController
public class StudentFilterController {
    @Autowired
    private StudentServiceFilter studentServiceFilter;
    @PostMapping("")
    public ResponseEntity<StudentFilterDTO> create(@RequestBody StudentFilterDTO dto) {
        return ResponseEntity.ok(studentServiceFilter.create(dto));
    }
    @PostMapping("/filter")
    public ResponseEntity<PageImpl<?>> filter(@RequestBody StudentFilterDTO dto,
                                              @RequestParam("page") Integer page,
                                              @RequestParam("size") Integer size) {
        return ResponseEntity.ok(studentServiceFilter.filter(dto, page, size));
    }


}
