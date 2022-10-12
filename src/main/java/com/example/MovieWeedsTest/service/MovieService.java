package com.example.MovieWeedsTest.service;


import com.example.MovieWeedsTest.dto.ResponseMovie;
import com.example.MovieWeedsTest.repository.MovieRepository;
import com.example.MovieWeedsTest.repository.querydsl.MovieQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieQueryDslRepository movieQueryDslRepository;

    public List<ResponseMovie> findAllService(Pageable pageable) {
        return movieQueryDslRepository.findAll(pageable).stream().map(ResponseMovie::new).collect(Collectors.toList());
    }

    public List<ResponseMovie> findAllMovieTypeBestService(String genres_name, Pageable pageable) {

        return movieQueryDslRepository.findAllMovieTypeBest(genres_name, pageable).stream().map(ResponseMovie::new).collect(Collectors.toList());
    }
}
