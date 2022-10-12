package com.example.MovieWeedsTest.repository.querydsl;

import com.example.MovieWeedsTest.domain.*;
import com.example.MovieWeedsTest.dto.ResponseMovie;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.example.MovieWeedsTest.domain.QGenre.genre;
import static com.example.MovieWeedsTest.domain.QGenreMovie.genreMovie;
import static com.example.MovieWeedsTest.domain.QMovie.movie;

@Repository
public class MovieQueryDslRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;


    public MovieQueryDslRepository(JPAQueryFactory queryFactory) {
        super(Movie.class);
        this.queryFactory = queryFactory;
    }

    public PageImpl<Movie> findAll(Pageable pageable) {
        JPAQuery<Movie> movieJPAQuery =
                queryFactory.select(movie).from(movie).join(movie.genreMovies).fetchJoin();
        List<Movie> result = getQuerydsl().applyPagination(pageable, movieJPAQuery).fetch();
        return new PageImpl<>(result,pageable,result.size());
    }

    public PageImpl<Movie> findAllMovieTypeBest(String genres_name, Pageable pageable) {

        List<Long> ids = queryFactory.select(movie.id).from(movie).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        JPAQuery<Movie> movieJPAQuery;
        if (genres_name != null){
            movieJPAQuery = queryFactory
                    .select(Projections.fields(movie, genre)).from(movie)
                    .join(movie.genreMovies)
                    .fetchJoin()
                    .where(movie.genreMovies.any().genre.name.eq(genres_name).and(movie.id.in(ids)))
                    .orderBy(movie.popularity.desc());
        } else {
            movieJPAQuery = queryFactory
                    .select(Projections.fields(movie, genre))
                    .from(movie).join(movie.genreMovies.any().genre, genre).fetchJoin();
        }
        List<Movie> result = getQuerydsl().applyPagination(pageable, movieJPAQuery).fetch();
        return new PageImpl<>(result,pageable,result.size());
    }


}
