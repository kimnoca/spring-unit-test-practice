package com.example.springunittestpractice.post.service;


import static org.assertj.core.api.Assertions.assertThat;

import com.example.springunittestpractice.post.domain.Post;
import com.example.springunittestpractice.post.dto.PostCreateDto;
import com.example.springunittestpractice.post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(SpringExtension.class)
public class PostServiceTest {

    PostService postService;

    @MockBean
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postService = new PostService(postRepository);
    }


    public PostCreateDto setUpCreatePost() {
        return PostCreateDto.builder()
                .title("제목1")
                .content("내용1")
                .build();
    }

    @DisplayName("게시물 생성")
    @Test
    void createPost() {

        // given
        Post post = Post.builder()
                .title("제목1")
                .content("내용1")
                .build();

        Mockito.when(postRepository.save(post)).thenReturn(post);

        // when
        Post insertPost = postService.savePost(setUpCreatePost());

        // then
        assertThat(insertPost.getContent()).isEqualTo(post.getContent());
        assertThat(insertPost.getTitle()).isEqualTo(post.getTitle());
    }


}
