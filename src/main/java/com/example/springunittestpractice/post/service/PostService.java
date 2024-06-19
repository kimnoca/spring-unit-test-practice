package com.example.springunittestpractice.post.service;

import com.example.springunittestpractice.post.domain.Post;
import com.example.springunittestpractice.post.dto.PostCreateDto;
import com.example.springunittestpractice.post.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

        return post;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
