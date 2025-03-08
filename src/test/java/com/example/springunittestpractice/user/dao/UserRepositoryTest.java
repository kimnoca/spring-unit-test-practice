package com.example.springunittestpractice.user.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.springunittestpractice.global.config.JpaConfig;
import com.example.springunittestpractice.user.domain.User;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;


@DataJpaTest
@Import(JpaConfig.class)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

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
        assertThat(resultUser.getCreatedAt()).isNotNull();

    }

    @Test
    @DisplayName("유저 email 중복 관련 체크")
    void userEmailConflict() {
        User user1 = User.builder()
            .email("kimnoca@naver.com")
            .nickname("김노카")
            .build();

        userRepository.save(user1);

        User user2 = User.builder()
            .email("kimnoca@naver.com")
            .nickname("김형준")
            .build();
        // DataIntegrityViolationException 는 Data 무결성 제약 관련 Exception 이다.
        // email column에 unique = true 설정을 해주었기때문에 같은 email로 save를 하면 exception 발생
        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.save(user2);
        });
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