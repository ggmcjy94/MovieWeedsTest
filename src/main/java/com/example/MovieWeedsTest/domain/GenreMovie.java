package com.example.MovieWeedsTest.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class GenreMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;




}
