package com.example.MovieWeedsTest.repository.querydsl;

import com.example.MovieWeedsTest.domain.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static com.example.MovieWeedsTest.domain.QMovie.movie;

@Repository
@Transactional(readOnly = true)
public class MovieQueryDslRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public MovieQueryDslRepository(JPAQueryFactory queryFactory) {
        super(Movie.class);
        this.queryFactory = queryFactory;
    }

    public PageImpl<Movie> findAllMovieTypeBest(Long genres_id, Pageable pageable) {
        JPAQuery<Movie> movieJPAQuery = queryFactory
                .select(movie).from(movie);
        if (genres_id != null) {
            movieJPAQuery.where(movie.genreMovies.any().genre.id.eq(genres_id));
        }
        List<Movie> result = getQuerydsl().applyPagination(pageable, movieJPAQuery.orderBy(movie.popularity.desc())).fetch();
        return new PageImpl<>(result,pageable,result.size());
    }
}
