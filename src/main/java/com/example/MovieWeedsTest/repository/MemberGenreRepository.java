package com.example.MovieWeedsTest.repository;

import com.example.MovieWeedsTest.domain.MemberGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MemberGenreRepository extends JpaRepository<MemberGenre, Long> {
}
