package com.example.springunittestpractice.post.repository;

import com.example.springunittestpractice.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
