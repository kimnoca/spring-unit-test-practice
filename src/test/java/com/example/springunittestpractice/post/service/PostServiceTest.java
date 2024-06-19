package com.example.springunittestpractice.post.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.example.springunittestpractice.post.domain.Post;
import com.example.springunittestpractice.post.dto.PostCreateDto;
import com.example.springunittestpractice.post.repository.PostRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @Test
    @DisplayName("게시물 생성")
    void createPost() {

        // given
        Post post = Post.builder()
                .title("제목1")
                .content("내용1")
                .build();

        ReflectionTestUtils.setField(post, "id", 1L);

        when(postRepository.save(post)).thenReturn(post);

        // when
        Post insertPost = postService.savePost(setUpCreatePost());

        // then
        assertThat(insertPost.getContent()).isEqualTo(post.getContent());
        assertThat(insertPost.getTitle()).isEqualTo(post.getTitle());
    }

    @Test
    @DisplayName("게시물 번호로 게시물 찾기")
    void findPostById() {

        Post post = Post.builder()
                .title("제목1")
                .content("내용1")
                .build();

        ReflectionTestUtils.setField(post, "id", 1L);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        // when
        Post foundPost = postService.findById(1L);

        // then
        assertThat(foundPost).isEqualTo(post);
    }

    @Test
    @DisplayName("없는 게시물 번호 에러처리")
    void exceptionHandlerFindByPostId() {

        // given
        Post post = Post.builder()
                .title("제목1")
                .content("내용1")
                .build();

        ReflectionTestUtils.setField(post, "id", 2L);
        when(postRepository.findById(2L)).thenReturn(Optional.of(post));

        // then
        assertThatThrownBy(() -> postService.findById(10L)).isInstanceOf(IllegalArgumentException.class);

    }
}