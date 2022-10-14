package com.example.MovieWeedsTest.dto.response;

import com.example.MovieWeedsTest.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMovie {

    private Long id;
    private String title;
    private String language;
    private String overview;
    private Integer popularity;
    private Double grade;

    private Integer gradeCount;
    private String posterPath;
    private LocalDate releaseDate;
    private Integer runtime;
    private Integer budget;
    private Integer revenue;

    private Long[] genres_id;

    public ResponseMovie(Movie m) {
        this.id = m.getId();
        this.title = m.getTitle();
        this.language = m.getLanguage();
        this.overview = m.getOverview();
        this.popularity = m.getPopularity();
        this.grade = m.getGrade();
        this.gradeCount = m.getGradeCount();
        this.posterPath = m.getPosterPath();
        this.releaseDate = m.getReleaseDate();
        this.runtime = m.getRuntime();
        this.budget = m.getBudget();
        this.revenue = m.getRevenue();
        List<ResponseTypeBestGenre> collect = m.getGenreMovies().stream().map(ResponseTypeBestGenre::new).toList();
        this.genres_id = new Long[collect.size()];
        for (int i = 0; i < collect.size(); i++) {
            this.genres_id[i] = collect.get(i).getId();
        }
    }

}
