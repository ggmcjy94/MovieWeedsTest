package com.example.MovieWeedsTest.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("유저가 발견 되지 않음");
    }
}
