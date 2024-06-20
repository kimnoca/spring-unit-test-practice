package com.example.springunittestpractice.post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostResponseDto {
    private String title;
    private String content;
}
