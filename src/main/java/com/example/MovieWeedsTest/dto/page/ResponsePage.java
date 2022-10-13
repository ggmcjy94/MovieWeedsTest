package com.example.MovieWeedsTest.dto.page;

import com.example.MovieWeedsTest.dto.ResponseMovie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePage<T>  {
    private ReleaseDate date;
    private Integer page;
    private T result;
    private Integer total_results;


    public ResponsePage(int pageNumber, List<ResponseMovie> responseMovies, int size) {
        this.page = pageNumber;
        this.result = (T) responseMovies;
        this.total_results = size;
    }


}
