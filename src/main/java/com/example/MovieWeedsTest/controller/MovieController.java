package com.example.MovieWeedsTest.controller;

import com.example.MovieWeedsTest.dto.ResponseMovieSneakPeek;
import com.example.MovieWeedsTest.dto.ResponseMovie;
import com.example.MovieWeedsTest.dto.page.ReleaseDate;
import com.example.MovieWeedsTest.dto.page.ResponsePage;
import com.example.MovieWeedsTest.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.example.MovieWeedsTest.domain.QMovie.movie;

//https://api.themoviedb.org/3/movie/436270?api_key=f7679056c8c57aa9820d494e90a8a8da&language=ko
@Slf4j
@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/type-best")
    public ResponseEntity<ResponsePage<List<ResponseMovie>>> getAllTypeBestMoviesController(@RequestParam(value = "genres", required = false) Long genres_id,
                                                                                            @RequestParam(value = "popularity", required = false, defaultValue = "desc") String popularity,
                                                                                            Pageable pageable) {

        log.info("popularity {}" , popularity);
        List<ResponseMovie> responseMovies = movieService.findAllMovieTypeBestService(popularity,genres_id, pageable);

        return new ResponseEntity<>(
                new ResponsePage<>(
                        pageable.getPageNumber(),
                        responseMovies,
                        responseMovies.size()), HttpStatus.OK);
    }

    @GetMapping("/sneak-peek")
    public ResponseEntity<ResponsePage<List<ResponseMovieSneakPeek>>> getAllSneakPeekMoviesController(@RequestParam(value = "genres", required = false) Long genres_id,
                                                                                                      @RequestParam(value = "popularity", required = false, defaultValue = "desc") String popularity,
                                                                                                      Pageable pageable) {

        List<ResponseMovieSneakPeek> responseMovies = movieService.findAllMovieSneakPeekService(genres_id, popularity,LocalDate.now(), pageable);
        return new ResponseEntity<>(
                new ResponsePage<>(
                        new ReleaseDate(LocalDate.now().plusMonths(2),LocalDate.now().plusDays(1)),
                        pageable.getPageNumber(),
                        responseMovies,
                        responseMovies.size()),HttpStatus.OK);
    }

    @GetMapping("/{movie_id}")
    public ResponseEntity<ResponseMovie> getMovieDetailController(@PathVariable("movie_id") Long movie_id) {
        return new ResponseEntity<ResponseMovie>(movieService.findById(movie_id), HttpStatus.OK);
    }

}
