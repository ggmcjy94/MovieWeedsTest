package com.example.MovieWeedsTest.service;


import com.example.MovieWeedsTest.dto.ResponseMovieSneakPeek;
import com.example.MovieWeedsTest.dto.ResponseMovie;
import com.example.MovieWeedsTest.repository.MovieRepository;
import com.example.MovieWeedsTest.repository.querydsl.MovieQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieQueryDslRepository movieQueryDslRepository;

    public List<ResponseMovie> findAllService(Pageable pageable) {
        return movieRepository.findAll(pageable).stream().map(ResponseMovie::new).collect(Collectors.toList());
    }

    public List<ResponseMovie> findAllMovieTypeBestService(String popularity, Long genres_id, Pageable pageable) {
        return movieQueryDslRepository.findAllMovieTypeBest(popularity.toLowerCase(),genres_id, pageable).stream().map(ResponseMovie::new).collect(Collectors.toList());
    }

    public List<ResponseMovieSneakPeek> findAllMovieSneakPeekService(Long genres_id, String popularity, LocalDate now, Pageable pageable) {
        return movieQueryDslRepository.findAllMovieSneakPeek(genres_id, popularity.toLowerCase(),now, pageable).stream().map(ResponseMovieSneakPeek::new).collect(Collectors.toList());
    }

    public ResponseMovie findById(Long movie_id) {
        return new ResponseMovie(
                movieRepository
                        .findById(movie_id)
                        .orElseThrow(() -> new EntityNotFoundException("존재하지않는 영화 정보 ["+movie_id+"]")));
    }
}
