package com.example.springunittestpractice.post.dao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.springunittestpractice.post.domain.Post;
import com.example.springunittestpractice.post.repository.PostRepository;
import com.example.springunittestpractice.user.dao.UserRepository;
import com.example.springunittestpractice.user.domain.User;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class PostRepositoryTest {

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EntityManager entityManager;

	@Test
	void saveAndFindOneTest() {

		User user = User.builder()
						.email("email")
						.nickname("nickname")
						.build();

		userRepository.save(user);

		Post post = Post.builder()
						.title("title")
						.content("content")
						.author(user)
						.build();

		postRepository.save(post);

		entityManager.flush();
		entityManager.clear();

		Post findPost = postRepository.findById(post.getId())
									  .orElseThrow();

		assertThat(findPost.getId()).isEqualTo(post.getId());
	}

}
