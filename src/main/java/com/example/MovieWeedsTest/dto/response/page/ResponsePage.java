package com.example.MovieWeedsTest.dto.response.page;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePage<T>  {
    private ReleaseDate date;
    private Integer page;
    private T result;
    private Integer total_results;

}
