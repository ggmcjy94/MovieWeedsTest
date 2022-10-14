package com.example.MovieWeedsTest.controller;

import com.example.MovieWeedsTest.dto.request.RequestMemberMovieGrade;
import com.example.MovieWeedsTest.dto.request.RequestMemberMoviePopularity;
import com.example.MovieWeedsTest.dto.response.ResponseMovieSneakPeek;
import com.example.MovieWeedsTest.dto.response.ResponseMovie;
import com.example.MovieWeedsTest.dto.response.ResponseMoviesRecommend;
import com.example.MovieWeedsTest.dto.response.ResponseMoviesTrending;
import com.example.MovieWeedsTest.dto.response.page.ReleaseDate;
import com.example.MovieWeedsTest.dto.response.page.ResponsePage;
import com.example.MovieWeedsTest.service.MemberService;
import com.example.MovieWeedsTest.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.List;

//https://api.themoviedb.org/3/movie/436270?api_key=f7679056c8c57aa9820d494e90a8a8da&language=ko
@Slf4j
@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MemberService memberService;


    @GetMapping("/type-best")
    public ResponseEntity<ResponsePage<?>> getAllTypeBestMoviesController(@RequestParam(value = "genres", required = false) Long genres_id,
                                                                                            @RequestParam(value = "popularity", required = false, defaultValue = "desc") String popularity,
                                                                                            Pageable pageable) {

        log.info("popularity {}" , popularity);
        List<ResponseMovie> responseMovies = movieService.findAllMovieTypeBestService(popularity,genres_id, pageable);

        return new ResponseEntity<>(
                ResponsePage
                        .builder()
                        .page(pageable.getPageNumber())
                        .result(responseMovies)
                        .total_results(responseMovies.size())
                        .build(), HttpStatus.OK);
    }

    @GetMapping("/sneak-peek")
    public ResponseEntity<ResponsePage<?>> getAllSneakPeekMoviesController(@RequestParam(value = "genres", required = false) Long genres_id,
                                                                                                      @RequestParam(value = "popularity", required = false, defaultValue = "desc") String popularity,
                                                                                                      Pageable pageable) {

        List<ResponseMovieSneakPeek> responseMovies = movieService.findAllMovieSneakPeekService(genres_id, popularity,LocalDate.now(), pageable);
        return new ResponseEntity<>(
                        ResponsePage
                                .builder()
                                .date(new ReleaseDate(LocalDate.now().plusMonths(2),LocalDate.now().plusDays(1)))
                                .page(pageable.getPageNumber())
                                .result(responseMovies)
                                .total_results(responseMovies.size())
                                .build(),HttpStatus.OK);
    }



    @GetMapping("/{movie_id}")
    public ResponseEntity<ResponseMovie> getMovieDetailController(@PathVariable("movie_id") Long movie_id) {
        return new ResponseEntity<ResponseMovie>(movieService.getMovieDetailService(movie_id), HttpStatus.OK);
    }

    @GetMapping("/recommend")
    public ResponseEntity<ResponsePage<?>> getRecommendMoviesController(@AuthenticationPrincipal UserDetails userDetails,
                                                                                              Pageable pageable) {
        List<ResponseMoviesRecommend> responseUserRecommendMovies = movieService.findAllMovieRecommendService(userDetails,pageable);
        return new ResponseEntity<>(
                ResponsePage
                        .builder()
                        .page(pageable.getPageNumber())
                        .result(responseUserRecommendMovies)
                        .total_results(responseUserRecommendMovies.size())
                        .build(), HttpStatus.OK);
    }


    /**
     * 기간별 트렌딩 구별은 좋아요 보다는 조회수로 조회 하는게 더 나은 방법인거 같다.
     */
    @GetMapping("/trending/{today_weekly}")
    public ResponseEntity<ResponsePage<?>> getTrendingMoviesController(@PathVariable("today_weekly") String today_weekly,
                                                                       Pageable pageable) {
        if (!(today_weekly.equals("day") || today_weekly.equals("week"))) {
            throw new IllegalArgumentException("잘못된 입력정보 /trending/[day, week]");
        }

        List<ResponseMoviesTrending> responseMoviesTrends = movieService.findAllMovieTrendingService(today_weekly,pageable);
        return new ResponseEntity<>(
                ResponsePage
                        .builder()
                        .page(pageable.getPageNumber())
                        .result(responseMoviesTrends)
                        .total_results(responseMoviesTrends.size())
                        .build(), HttpStatus.OK);
    }

    @PostMapping("/{movie_id}/give-grade")
    public ResponseEntity<?> giveGradeController(@AuthenticationPrincipal UserDetails userDetails,
                                                 @PathVariable("movie_id") Long movie_id,
                                                 @Validated @RequestBody RequestMemberMovieGrade request) {

        movieService.giveGradeService(movie_id,userDetails, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{movie_id}/check-popularity")
    public ResponseEntity<?> giveGradeController(@AuthenticationPrincipal UserDetails userDetails,
                                                 @PathVariable("movie_id") Long movie_id,
                                                 @RequestBody RequestMemberMoviePopularity request) {

        movieService.checkPopularityService(movie_id,userDetails, request);
        return ResponseEntity.ok().build();
    }

}
