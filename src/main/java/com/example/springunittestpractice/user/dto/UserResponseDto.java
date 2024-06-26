package com.example.springunittestpractice.user.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {
    private String email;
    private String nickname;

}
