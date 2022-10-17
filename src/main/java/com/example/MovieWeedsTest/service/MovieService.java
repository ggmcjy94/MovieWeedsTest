package com.example.MovieWeedsTest.service;


import com.example.MovieWeedsTest.domain.Member;
import com.example.MovieWeedsTest.domain.MemberMovie;
import com.example.MovieWeedsTest.domain.Movie;
import com.example.MovieWeedsTest.dto.request.RequestMemberMovieGrade;
import com.example.MovieWeedsTest.dto.request.RequestMemberMoviePopularity;
import com.example.MovieWeedsTest.dto.response.ResponseMovieSneakPeek;
import com.example.MovieWeedsTest.dto.response.ResponseMovie;
import com.example.MovieWeedsTest.dto.response.ResponseMoviesRecommend;
import com.example.MovieWeedsTest.dto.response.ResponseMoviesTrending;
import com.example.MovieWeedsTest.repository.MemberMovieRepository;
import com.example.MovieWeedsTest.repository.MovieRepository;
import com.example.MovieWeedsTest.repository.querydsl.MovieQueryDslRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MemberService memberService;
    private final MovieRepository movieRepository;
    private final MovieQueryDslRepository movieQueryDslRepository;
    private final MemberMovieRepository memberMovieRepository;

    public List<ResponseMovie> findAllService(Pageable pageable) {
        return movieRepository.findAll(pageable).stream().map(ResponseMovie::new).collect(Collectors.toList());
    }

    public List<ResponseMovie> findAllMovieTypeBestService(String popularity, Long genres_id, Pageable pageable) {
        return movieQueryDslRepository.findAllMovieTypeBest(popularity.toLowerCase(),genres_id, pageable).stream().map(ResponseMovie::new).collect(Collectors.toList());
    }

    public List<ResponseMovieSneakPeek> findAllMovieSneakPeekService(Long genres_id, String popularity, LocalDate now, Pageable pageable) {
        return movieQueryDslRepository.findAllMovieSneakPeek(genres_id, popularity.toLowerCase(),now, pageable).stream().map(ResponseMovieSneakPeek::new).collect(Collectors.toList());
    }

    public ResponseMovie getMovieDetailService(Long movie_id) {
        return new ResponseMovie(
                findById(movie_id));
    }

    public List<ResponseMoviesTrending> findAllMovieTrendingService(String time, Pageable pageable) {
        return movieQueryDslRepository.findAllMoviesTrending(time, pageable).stream().map(ResponseMoviesTrending::new).collect(Collectors.toList());
    }


    public List<ResponseMoviesRecommend> findAllMovieRecommendService(UserDetails userDetails, Pageable pageable) {

        PageImpl<Movie> movies;
        if (userDetails != null) {

            Member member = memberService.getAuthenticationUser(userDetails);
            log.info("member.getId() {}" , member.getId());

            List<Long> memberRecommendMovies_id = member.getMemberGenres().stream().map(mg -> mg.getGenre().getId()).toList();
            movies = movieQueryDslRepository.findAllMemberMovieRecommendService(memberRecommendMovies_id, pageable);

        } else {
            movies = movieRepository.findAllByOrderByPopularityDesc(pageable);
        }

        log.info("movies.getTotalElements() {}" , movies.getTotalElements());
        // 해당 유저에 대한 추천 영화 데이터가 없는 경우 인기순으로 뿌려 준다.
        if (movies.getTotalElements() == 0) {
            movies = movieRepository.findAllByOrderByPopularityDesc(pageable);
        }

        return movies.stream().map(ResponseMoviesRecommend::new).collect(Collectors.toList());
    }

    public void giveGradeService(Long movie_id, UserDetails userDetails, RequestMemberMovieGrade grade) {
        Member member = memberService.getAuthenticationUser(userDetails);
        Movie movie = findById(movie_id);
        MemberMovie memberMovie = findByMemberAndMovie(member, movie);
        if (memberMovie != null) {
            memberMovie.updateGrade(grade);
        } else {
            memberMovieRepository.save(new MemberMovie(member, movie, grade));
        }
    }

    public void checkPopularityService(Long movie_id, UserDetails userDetails, RequestMemberMoviePopularity popularity) {
        Member member = memberService.getAuthenticationUser(userDetails);
        Movie movie = findById(movie_id);
        MemberMovie memberMovie = findByMemberAndMovie(member, movie);
        if (memberMovie != null) {
            memberMovie.updatePopularity(popularity);
        } else {
            memberMovieRepository.save(new MemberMovie(member, movie, popularity));
        }
    }

    private MemberMovie findByMemberAndMovie(Member member,Movie movie) {
        return memberMovieRepository.findByMemberAndMovie(member, movie).orElse(null);

    }

    private Movie findById(Long movie_id) {
        return movieRepository
                .findById(movie_id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지않는 영화 정보 [" + movie_id + "]"));
    }




}
