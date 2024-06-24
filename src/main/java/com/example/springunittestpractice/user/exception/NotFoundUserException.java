package com.example.springunittestpractice.user.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotFoundUserException extends RuntimeException {
    private String message;
}
