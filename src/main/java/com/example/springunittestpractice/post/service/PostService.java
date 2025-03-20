package com.example.springunittestpractice.post.service;

import com.example.springunittestpractice.global.exception.BusinessLogicException;
import com.example.springunittestpractice.post.domain.Post;
import com.example.springunittestpractice.post.dto.PostCreateDto;
import com.example.springunittestpractice.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public Post savePost(PostCreateDto postCreateDto) {
        Post post = Post.builder()
            .title(postCreateDto.getTitle())
            .content(postCreateDto.getContent())
            .build();

        postRepository.save(post);

        return post;
    }


    public Post findById(Long id) {
        return postRepository.findById(id)
            .orElseThrow(() -> new BusinessLogicException("해당 게시물이 없습니다.", HttpStatus.NOT_FOUND.value()));
    }

}
