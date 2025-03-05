package com.example.springunittestpractice.post.domain;


import com.example.springunittestpractice.user.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostTest {

    User user;

    @BeforeEach
    void setupUser() {
        user = User.builder()
                   .email("email")
                   .nickname("nickname")
                   .build();
    }

    @Test
    void savePostTest() {
        Post post = Post.builder()
                        .title("title")
                        .content("content")
                        .build();

        post.addUser(user);

        Assertions.assertThat(post.getTitle()).isEqualTo("title");
        Assertions.assertThat(user.getPosts().size()).isEqualTo(1);
    }
}
