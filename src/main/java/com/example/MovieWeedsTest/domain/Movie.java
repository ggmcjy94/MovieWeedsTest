package com.example.MovieWeedsTest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 영화 영어 제목 (한국어 기준)
    private String language; // 영화 언어
    private String overview; // 영화 개요 (한국어 기준)
    private Integer popularity; // 토탈 좋아요
    private Double grade; // 영화 평균 평점
    private Integer gradeCount; // 평점 측정인수
    private String posterPath; // 영화 포스터
    @Column(nullable = false)
    private LocalDate releaseDate; // 개봉 날짜 (한국 기준)
    private Integer runtime; // 영화 시간
    private Integer budget; //제작비
    private Integer revenue; // 수익

    @OneToMany(mappedBy = "movie")
    private List<GenreMovie> genreMovies = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<MemberMovie> memberMovies = new ArrayList<>();

    public Movie(Long id, String title, String language, String overview, Integer popularity, Double grade, Integer gradeCount, String posterPath, LocalDate releaseDate, Integer runtime, Integer budget, Integer revenue) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.overview = overview;
        this.popularity = popularity;
        this.grade = grade;
        this.gradeCount = gradeCount;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.budget = budget;
        this.revenue = revenue;
    }

    public void plusGradeCount() {
        this.gradeCount+=1;
    }

}
