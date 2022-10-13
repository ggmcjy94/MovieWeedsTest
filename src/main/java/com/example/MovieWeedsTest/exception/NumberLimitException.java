package com.example.MovieWeedsTest.exception;

public class NumberLimitException extends RuntimeException{

    public NumberLimitException() {
        super("3개 이하 선텍");
    }
}
