package com.abdo.learn.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.abdo.learn.config.JwtAuthenticationFilter;
import com.abdo.learn.mapper.PostMapper;
import com.abdo.learn.model.dto.request.CreatePostRequest;
import com.abdo.learn.model.dto.response.PostResponse;
import com.abdo.learn.model.entity.PostEntity;
import com.abdo.learn.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = PostController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private PostService postService;

    private CreatePostRequest createPostRequest;
    private PostResponse postResponse;

    @BeforeEach
    void init() {
        createPostRequest = CreatePostRequest.builder()
                .content("test post")
                .build();
        // PostEntity post =
        // PostMapper.INSTANCE.PostRequestToPostEntity(createPostRequest);
        // postResponse =
        // PostMapper.INSTANCE.PostEntityToPostResponse(PostMapper.INSTANCE.PostRequestToPostEntity(createPostRequest));
    }

    @Test
    public void PostController_CreatePost_ReturnPost() throws Exception {

        when(postService.createPost(any(createPostRequest.getClass()))).thenAnswer(
                invocations -> PostMapper.INSTANCE.PostEntityToPostResponse(
                        PostMapper.INSTANCE.PostRequestToPostEntity(invocations.getArgument(0))));

        ResultActions response = mockMvc.perform(post("/api/v1/post/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createPostRequest)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", CoreMatchers.is(createPostRequest.content())));
    }

}
