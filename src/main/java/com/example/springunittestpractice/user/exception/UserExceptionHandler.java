package com.example.springunittestpractice.user.exception;

import global.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(NotFoundUserException.class)
    ResponseEntity<?> notFoundUserException(NotFoundUserException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .httpStatus(404)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(AlreadyExistUserException.class)
    ResponseEntity<?> alreadyExistUserException(AlreadyExistUserException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .httpStatus(409)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
