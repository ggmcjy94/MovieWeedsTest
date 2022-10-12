package com.example.MovieWeedsTest.controller;

import com.example.MovieWeedsTest.dto.ResponseTypeBestMovie;
import com.example.MovieWeedsTest.dto.ResponsePage;
import com.example.MovieWeedsTest.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//https://api.themoviedb.org/3/movie/436270?api_key=f7679056c8c57aa9820d494e90a8a8da&language=ko
@Slf4j
@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<ResponsePage<List<ResponseTypeBestMovie>>> getAllMoviesController(Pageable pageable) {
        return new ResponseEntity<>(
                new ResponsePage<>(
                        pageable.getPageNumber(),
                        movieService.findAllService(pageable),
                        movieService.findAllService(pageable).size()), HttpStatus.OK);
    }

    @GetMapping("/type-best")
    public ResponseEntity<ResponsePage<List<ResponseTypeBestMovie>>> getAllTypeBestMoviesController(@RequestParam(value = "genres", required = false) Long genres_id,
                                                                                                    Pageable pageable) {

        List<ResponseTypeBestMovie> responseMovies = movieService.findAllMovieTypeBestService(genres_id, pageable);

        return new ResponseEntity<>(
                new ResponsePage<>(
                        pageable.getPageNumber(),
                        responseMovies,
                        responseMovies.size()), HttpStatus.OK);
    }


}
