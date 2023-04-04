package com.example.homework12.exception;

public class AppBadRequestException extends Throwable {
    public AppBadRequestException(String message) {
        super(message);
    }
}
