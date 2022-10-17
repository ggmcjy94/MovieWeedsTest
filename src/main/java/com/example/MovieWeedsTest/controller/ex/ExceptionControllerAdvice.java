package com.example.MovieWeedsTest.controller.ex;


import com.example.MovieWeedsTest.dto.response.ex.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * 공통 api 예외 처리 필요한 예외처리 추가
 */

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseException exceptionError(Exception e) {
        log.error("Exception ex", e);
        return new ResponseException(e.getMessage());
    }

}
