package com.example.springunittestpractice.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springunittestpractice.global.exception.BusinessLogicException;
import com.example.springunittestpractice.user.dao.UserRepository;
import com.example.springunittestpractice.user.domain.User;
import com.example.springunittestpractice.user.dto.UserCreateDto;
import com.example.springunittestpractice.user.dto.UserResponseDto;
import com.example.springunittestpractice.user.dto.UserUpdateDto;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    //@Spy -> 공부 필요
    @InjectMocks
    UserService userService;

    @Test
    void createUser() {
        User user = User.builder()
            .email("kimnoca@naver.com")
            .nickname("김노카")
            .build();

        UserCreateDto userCreateDto = UserCreateDto.builder()
            .email("kimnoca@naver.com")
            .nickname("김노카")
            .build();

        when(userRepository.save(any())).thenReturn(user);

        UserResponseDto response = userService.createUser(userCreateDto);

        assertThat(response.email()).isEqualTo(user.getEmail());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUserFail() {
        User user = User.builder()
            .email("kimnoca@naver.com")
            .nickname("김노카")
            .build();

        UserCreateDto userCreateDto = UserCreateDto.builder()
            .email("kimnoca@naver.com")
            .nickname("김노카")
            .build();

        when(userRepository.findByEmail("kimnoca@naver.com")).thenReturn(Optional.ofNullable(user));

        assertThrows(BusinessLogicException.class, () -> {
            userService.createUser(userCreateDto);
        });

    }

    @Test
    void changeNicknameTest() {
        User user = User.builder()
            .email("kimnoca@naver.com")
            .nickname("김노카")
            .build();

        UserUpdateDto request = UserUpdateDto.builder()
            .newUserNickname("newNickname")
            .build();

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        UserResponseDto userResponseDto = userService.updateUser(1L, request);

        assertThat(userResponseDto.nickname()).isEqualTo(request.getNewUserNickname());
    }
}