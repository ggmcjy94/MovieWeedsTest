package com.example.MovieWeedsTest.domain;

import com.example.MovieWeedsTest.dto.request.RequestMemberMovieGrade;
import com.example.MovieWeedsTest.dto.request.RequestMemberMoviePopularity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private Double userGrade = 0.0; //평점 점수 (1~5)
    private boolean popularity = false; // 좋아요 체크 여부
    private LocalDate popularityCreatedDate; //초기 좋아요 체크 날짜 트렌드분석


    public MemberMovie(Member member, Movie movie, RequestMemberMovieGrade requestMemberMovieGrade) {
        this.member = member;
        this.movie = movie;
        this.userGrade = requestMemberMovieGrade.getGrade();
        this.movie.plusGradeCount();
    }

    public MemberMovie(Member member, Movie movie, RequestMemberMoviePopularity requestMemberMoviePopularity) {
        this.member = member;
        this.movie = movie;
        this.popularity = requestMemberMoviePopularity.getPopularity();
        this.popularityCreatedDate = LocalDate.now();
    }

    public void updateGrade(RequestMemberMovieGrade requestMemberMovieGrade) {
        this.userGrade = requestMemberMovieGrade.getGrade();
    }

    public void updatePopularity(RequestMemberMoviePopularity requestMemberMoviePopularity) {
        this.popularity = requestMemberMoviePopularity.getPopularity();
    }
}
