package com.example.springunittestpractice.user.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.example.springunittestpractice.user.dto.UserCreateDto;
import com.example.springunittestpractice.user.dto.UserResponseDto;
import com.example.springunittestpractice.user.exception.AlreadyExistUserException;
import com.example.springunittestpractice.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("유저 생성")
    void createUser() throws Exception {

        // given
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .email("kimnoca@naver.com")
                .nickname("김노카")
                .build();

        // any() -> 이렇게 테스트를 돌리면 무슨 의미가 있는지 아직 잘 모르겠다
        given(userService.createUser(any())).willReturn(UserResponseDto.builder()
                .email("kimnoca@naver.com")
                .nickname("김노카")
                .build());

        // when and then
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("nickname").value("김노카"))
                .andExpect(MockMvcResultMatchers.jsonPath("email").value("kimnoca@naver.com"));
    }

    @Test
    @DisplayName("유저 생성 실패")
    void createUserFail() throws Exception {
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .email("kimnoca@naver.com")
                .nickname("김노카")
                .build();

        // any() -> 이렇게 테스트를 돌리면 무슨 의미가 있는지 아직 잘 모르겠다
        when(userService.createUser(any(UserCreateDto.class)))
                .thenThrow(new AlreadyExistUserException("이미 존재하는 email 입니다."));

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("httpStatus").value(409))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("이미 존재하는 email 입니다."));

    }
}