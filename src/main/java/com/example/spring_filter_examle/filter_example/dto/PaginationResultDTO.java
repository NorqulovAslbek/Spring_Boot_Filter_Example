package com.example.spring_filter_examle.filter_example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaginationResultDTO<T> {
    private List<T> list;
    private Long totalSize;
}
