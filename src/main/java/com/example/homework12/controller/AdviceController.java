package com.example.homework12.controller;


import com.example.homework12.exception.AppBadRequestException;
import com.example.homework12.exception.PhoneAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler({AppBadRequestException.class, PhoneAlreadyExistsException.class})
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
