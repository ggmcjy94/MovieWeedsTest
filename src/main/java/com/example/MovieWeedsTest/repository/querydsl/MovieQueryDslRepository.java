package com.example.MovieWeedsTest.repository.querydsl;

import com.example.MovieWeedsTest.domain.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.example.MovieWeedsTest.domain.QMovie.movie;

@Repository
@Transactional(readOnly = true)
public class MovieQueryDslRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public MovieQueryDslRepository(JPAQueryFactory queryFactory) {
        super(Movie.class);
        this.queryFactory = queryFactory;
    }

    public PageImpl<Movie> findAllMovieTypeBest(String popularity, Long genres_id, Pageable pageable) {
        JPAQuery<Movie> movieJPAQuery = basicSelect();

        if (genres_id != null) {
            movieJPAQuery.where(movie.genreMovies.any().genre.id.eq(genres_id));
        }

        popularityExists(popularity, movieJPAQuery);

        List<Movie> result = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, movieJPAQuery).fetch();
        return new PageImpl<>(result,pageable,result.size());
    }

    public PageImpl<Movie> findAllMovieSneakPeek(Long genres_id, String popularity, LocalDate now, Pageable pageable) {
        JPAQuery<Movie> movieJPAQuery = basicSelect();

        movieJPAQuery.where(movie.releaseDate.between(now.plusDays(1), now.plusMonths(2)));

        if (genres_id != null) {
            movieJPAQuery = movieJPAQuery.where(movie.genreMovies.any().genre.id.eq(genres_id)
                            .and(movie.releaseDate.between(now.plusDays(1), now.plusMonths(2))));
        }

        popularityExists(popularity, movieJPAQuery);

        List<Movie> result = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, movieJPAQuery).fetch();
        return new PageImpl<>(result, pageable, result.size());
    }


    private void popularityExists(String popularity, JPAQuery<Movie> movieJPAQuery) {
        if (popularity.equals("desc")) {
            movieJPAQuery.orderBy(movie.popularity.desc());
        } else if (popularity.equals("asc")) {
            movieJPAQuery.orderBy(movie.popularity.asc());
        } else {
            throw new IllegalArgumentException("올바르지 않은 입력 정보[desc, asc]");
        }
    }


    private JPAQuery<Movie> basicSelect() {
        return queryFactory.selectFrom(movie);
    }

    private boolean popularityExists(String popularity) {
        if (popularity != null) {
            popularity = popularity.toLowerCase();
            return true;
        }
        return false;
    }
}
