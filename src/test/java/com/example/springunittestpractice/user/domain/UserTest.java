package com.example.springunittestpractice.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    @DisplayName("유저 생성 테스트")
    void creatUser() {
        User user = User.builder()
                .email("kimnoca@naver.com")
                .nickname("김노카")
                .build();

        assertThat(user.getEmail()).isEqualTo("kimnoca@naver.com");
        assertThat(user.getNickname()).isEqualTo("김노카");
    }
    
    @Test
    @DisplayName("유저 닉네임 변경 테스트")
    void changeUserNickname() {
        User user = User.builder()
                .email("kimnoca@naver.com")
                .nickname("김노카")
                .build();
        
        user.changeUserNickname("김말숙");
        
        assertThat(user.getNickname()).isEqualTo("김말숙");
    }
}