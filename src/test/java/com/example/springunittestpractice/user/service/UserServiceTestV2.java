package com.example.springunittestpractice.user.service;

import static org.assertj.core.api.Assertions.*;

import com.example.springunittestpractice.user.dao.UserRepository;
import com.example.springunittestpractice.user.domain.User;
import com.example.springunittestpractice.user.dto.UserResponseDto;
import com.example.springunittestpractice.user.dto.UserUpdateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTestV2 {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService service;

    @Test
    void changeNicknameTest() {
        User user = User.builder()
            .email("kimnoca@naver.com")
            .nickname("김노카")
            .build();

        userRepository.save(user);

        service.updateUser(user.getId(), new UserUpdateDto("newNickname"));

        UserResponseDto response = service.findById(user.getId());

        assertThat(response.nickname()).isEqualTo("newNickname");
    }
}
