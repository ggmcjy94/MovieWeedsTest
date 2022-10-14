package com.example.MovieWeedsTest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestMemberMovieGrade {

    @DecimalMin(value = "0.0")
    @DecimalMax(value="5.0")
    private Double grade;
}
