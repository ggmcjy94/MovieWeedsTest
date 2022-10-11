package com.example.MovieWeedsTest.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 영화 제목 (한국어 기준)
    private String language; // 영화 언어
    private String overview; // 영화 개요 (한국어 기준)
    private Integer popularity; // 인기 좋아요 평점
    private String posterPath; // 영화 포스터

    @Column(nullable = false)
    private LocalDateTime releaseDate; // 개봉 날짜 (한국 기준)
    private Integer runtime; // 영화 시간
    private Integer budget; //제작비
    private Integer revenue; // 수익

    @OneToMany(mappedBy = "movie")
    private List<GenreMovie> genreMovies = new ArrayList<>();
}
