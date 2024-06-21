package com.example.springunittestpractice.user.dao;

import com.example.springunittestpractice.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
