package com.example.springunittestpractice.user.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserCreateDto {
    private String email;
    private String nickname;
}
