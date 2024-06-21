package com.example.springunittestpractice.user.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springunittestpractice.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("유저 만들기 테스트")
    void userCreate() {

        User user = User.builder()
                .email("kimnoca@naver.com")
                .nickname("김노카")
                .build();

        User resultUser = userRepository.save(user);

        assertThat(resultUser.getEmail()).isEqualTo("kimnoca@naver.com");
        assertThat(resultUser.getNickname()).isEqualTo("김노카");
    }

    @Test
    @DisplayName("모든 유저 조회 테스트")
    void findAll() {
        User user1 = User.builder()
                .email("example@naver.com")
                .nickname("김형준")
                .build();

        User user2 = User.builder()
                .email("kimnoca@naver.com")
                .nickname("김노카")
                .build();

        List<User> users = new ArrayList<>(List.of(user1, user2));

        userRepository.saveAll(users);

        List<User> findUsers = userRepository.findAll();

        assertThat(findUsers).isNotNull();

        assertThat(findUsers.size()).isEqualTo(2);
    }

}