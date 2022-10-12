package com.example.MovieWeedsTest.dto;

import com.example.MovieWeedsTest.domain.GenreMovie;
import com.example.MovieWeedsTest.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMovie {

    private Long id;
    private String title;
    private String language;
    private String overview;
    private Double popularity;
    private Double grade;

    private Integer gradeCount;
    private String posterPath;
    private String status;
    private LocalDateTime releaseDate;
    private Integer runtime;
    private Integer budget;
    private Integer revenue;
    private List<ResponseGenre> genres;

    public ResponseMovie(Movie m) {
        this.id = m.getId();
        this.title = m.getTitle();
        this.language = m.getLanguage();
        this.overview = m.getOverview();
        this.popularity = m.getPopularity();
        this.grade = m.getGrade();
        this.gradeCount = m.getGradeCount();
        this.posterPath = m.getPosterPath();
        this.status = m.getStatus();
        this.releaseDate = m.getReleaseDate();
        this.runtime = m.getRuntime();
        this.budget = m.getBudget();
        this.revenue = m.getRevenue();
        this.genres = m.getGenreMovies().stream().map(ResponseGenre::new).collect(Collectors.toList());
    }

    public ResponseMovie(GenreMovie gm) {
        this.id = gm.getMovie().getId();
        this.title = gm.getMovie().getTitle();
        this.language = gm.getMovie().getLanguage();
        this.overview = gm.getMovie().getOverview();
        this.popularity = gm.getMovie().getPopularity();
        this.grade = gm.getMovie().getGrade();
        this.gradeCount = gm.getMovie().getGradeCount();
        this.posterPath = gm.getMovie().getPosterPath();
        this.status = gm.getMovie().getStatus();
        this.releaseDate = gm.getMovie().getReleaseDate();
        this.runtime = gm.getMovie().getRuntime();
        this.budget = gm.getMovie().getBudget();
        this.revenue = gm.getMovie().getRevenue();
        this.genres = gm.getMovie().getGenreMovies().stream().map(ResponseGenre::new).collect(Collectors.toList());
    }
}
