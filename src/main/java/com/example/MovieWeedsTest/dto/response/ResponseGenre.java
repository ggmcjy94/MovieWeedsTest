package com.example.MovieWeedsTest.dto.response;

import com.example.MovieWeedsTest.domain.GenreMovie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGenre {

    private Long id;
    private String name;

    public ResponseGenre(GenreMovie g) {
        this.id = g.getGenre().getId();
        this.name = g.getGenre().getName();
    }
}
