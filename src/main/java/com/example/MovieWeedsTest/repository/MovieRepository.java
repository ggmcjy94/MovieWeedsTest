package com.example.MovieWeedsTest.repository;

import com.example.MovieWeedsTest.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
