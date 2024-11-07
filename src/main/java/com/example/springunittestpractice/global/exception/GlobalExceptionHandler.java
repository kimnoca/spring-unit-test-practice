package com.example.springunittestpractice.global.exception;

import com.example.springunittestpractice.global.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<?> handleCustomException(BusinessLogicException ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(ex.getHttpStatusCode())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getHttpStatusCode()));
    }
}
