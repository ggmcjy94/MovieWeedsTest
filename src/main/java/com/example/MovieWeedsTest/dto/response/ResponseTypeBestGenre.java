package com.example.MovieWeedsTest.dto.response;

import com.example.MovieWeedsTest.domain.GenreMovie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTypeBestGenre {
    private Long id;

    public ResponseTypeBestGenre(GenreMovie genreMovie) {
        this.id = genreMovie.getGenre().getId();
    }
}
