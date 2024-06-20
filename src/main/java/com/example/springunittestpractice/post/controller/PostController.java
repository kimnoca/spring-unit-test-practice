package com.example.springunittestpractice.post.controller;


import com.example.springunittestpractice.post.domain.Post;
import com.example.springunittestpractice.post.dto.PostCreateDto;
import com.example.springunittestpractice.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController {

    private final PostService postService;

    @PostMapping()
    public ResponseEntity<?> createPost(@RequestBody PostCreateDto postCreateDto) {
        Post post = postService.savePost(postCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("{postId}")
    public ResponseEntity<?> findPostById(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findById(postId));
    }

}
