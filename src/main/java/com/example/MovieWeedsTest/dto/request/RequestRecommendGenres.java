package com.example.MovieWeedsTest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRecommendGenres {
    private List<Long> genres_id;
}
