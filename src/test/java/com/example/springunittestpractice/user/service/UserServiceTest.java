package com.example.springunittestpractice.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.springunittestpractice.user.dao.UserRepository;
import com.example.springunittestpractice.user.domain.User;
import com.example.springunittestpractice.user.dto.UserCreateDto;
import com.example.springunittestpractice.user.dto.UserResponseDto;

import java.util.Optional;

import com.example.springunittestpractice.global.exception.BusinessLogicException;
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

//        verify(userService, times(1)).createUser(userCreateDto); -> @Spy 하면 잘 돌아감

        assertThat(response.getEmail()).isEqualTo(user.getEmail());
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
}