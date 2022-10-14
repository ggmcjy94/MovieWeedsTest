package com.example.MovieWeedsTest.repository;

import com.example.MovieWeedsTest.domain.Member;
import com.example.MovieWeedsTest.domain.MemberMovie;
import com.example.MovieWeedsTest.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional(readOnly = true)
public interface MemberMovieRepository extends JpaRepository<MemberMovie, Long> {
    Optional<MemberMovie> findByMemberAndMovie(Member member, Movie movie);

}
