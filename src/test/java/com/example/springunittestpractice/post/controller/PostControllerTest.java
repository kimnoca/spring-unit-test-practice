package com.example.springunittestpractice.post.controller;

import static org.mockito.Mockito.when;

import com.example.springunittestpractice.post.domain.Post;
import com.example.springunittestpractice.post.dto.PostCreateDto;
import com.example.springunittestpractice.post.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.springunittestpractice.global.exception.BusinessLogicException;
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


@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PostService postService;

    @Test
    @DisplayName("게시물 생성")
    void createPost() throws Exception {
        // given

        PostCreateDto postCreateDto = PostCreateDto.builder()
                .title("제목1")
                .content("내용1")
                .build();

        Post post = Post.builder()
                .id(1L)
                .title("제목1")
                .content("내용1")
                .build();

        when(postService.savePost(postCreateDto)).thenReturn(post);

        // when and then
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postCreateDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
        // 실제 응답과 다른점이 있는거 같음 -> Response body 부분이 없음.
    }


    @Test
    @DisplayName("게시물 단건 조회")
    void findByIdTest() throws Exception {
        // given
        Post post = Post.builder()
                .title("제목1")
                .content("내용1")
                .build();

        // when
        when(postService.findById(1L)).thenReturn(post);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/post/{postId}", 1).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("title").value("제목1"))
                .andExpect(MockMvcResultMatchers.jsonPath("content").value("내용1"));
    }

    @Test
    @DisplayName("게시물 단건 조회 실패")
    void findByIDFail() throws Exception {

        when(postService.findById(2L)).thenThrow(new BusinessLogicException("해당 게시물이 없습니다.", 404));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/post/{postId}", 2).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("httpStatus").value("404"))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("해당 게시물이 없습니다."));
    }

}