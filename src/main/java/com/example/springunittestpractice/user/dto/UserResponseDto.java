package com.example.springunittestpractice.user.dto;


import com.example.springunittestpractice.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "유저 정보 응답 DTO")
public record UserResponseDto(
    @Schema(description = "user email", example = "example@example.com")
    String email,
    @Schema(description = "user nickname", example = "example")
    String nickname
) {

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getEmail(), user.getNickname());
    }
}