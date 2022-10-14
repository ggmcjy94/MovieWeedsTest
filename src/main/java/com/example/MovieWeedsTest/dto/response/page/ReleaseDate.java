package com.example.MovieWeedsTest.dto.response.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReleaseDate {

    private LocalDate maxiMum;
    private LocalDate miniMum;
}
