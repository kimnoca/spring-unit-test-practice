package com.example.springunittestpractice.post.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springunittestpractice.post.domain.Post;
import com.example.springunittestpractice.post.repository.PostRepository;
import com.example.springunittestpractice.user.dao.UserRepository;
import com.example.springunittestpractice.user.domain.User;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    User user;


    @BeforeEach
    void setupAuthor() {
        user = User.builder()
                   .email("email")
                   .nickname("nickname")
                   .build();

        userRepository.save(user);
    }

    @Test
    void saveAndFindOneTest() {

        Post post = Post.builder()
                        .title("title")
                        .content("content")
                        .user(user)
                        .build();

        post.addUser(user);

        postRepository.save(post);

        entityManager.flush();
        entityManager.clear();

        Post findPost = postRepository.findById(post.getId())
                                      .orElseThrow();

        assertThat(findPost.getId()).isEqualTo(post.getId());
    }

    @Test
    void findPostByUserId() {

        Post post = Post.builder()
                        .title("title")
                        .content("content")
                        .user(user)
                        .build();

        post.addUser(user);

        postRepository.save(post);

        Post post2 = Post.builder()
                         .title("title")
                         .content("content")
                         .user(user)
                         .build();

        post2.addUser(user);

        postRepository.save(post2);

        entityManager.flush();
        entityManager.clear();

        List<Post> postList = postRepository.findByUserId(user.getId());
        assertThat(postList.size()).isEqualTo(2);
    }
}
