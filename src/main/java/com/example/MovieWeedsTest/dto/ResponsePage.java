package com.example.MovieWeedsTest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePage<T>  {
    private Integer page;
    private T result;
    private Integer total_results;


}
