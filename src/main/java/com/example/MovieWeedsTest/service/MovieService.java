package com.example.MovieWeedsTest.service;


import com.example.MovieWeedsTest.dto.ResponseTypeBestMovie;
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

    public List<ResponseTypeBestMovie> findAllService(Pageable pageable) {
        return movieRepository.findAll(pageable).stream().map(ResponseTypeBestMovie::new).collect(Collectors.toList());
    }

    public List<ResponseTypeBestMovie> findAllMovieTypeBestService(Long genres_id, Pageable pageable) {
        return movieQueryDslRepository.findAllMovieTypeBest(genres_id, pageable).stream().map(ResponseTypeBestMovie::new).collect(Collectors.toList());
    }
}
