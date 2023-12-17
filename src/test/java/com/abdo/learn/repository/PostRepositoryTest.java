package com.abdo.learn.repository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.abdo.learn.model.entity.PostEntity;
import com.abdo.learn.model.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    private PostEntity postEntity;
    private UserEntity userEntity;

    @BeforeEach
    public void init() {
        userEntity = UserEntity.builder()
                .email("test@mail.com")
                .password("password")
                .name("user name")
                .build();
        postEntity = PostEntity.builder()
                .content("post")
                .user(userEntity)
                .build();
        userRepository.save(userEntity);

    }

    @Test
    public void PostRepository_Save_returnSavedPost() {

        PostEntity savedPost = postRepository.save(postEntity);

        Assertions.assertThat(savedPost.getId()).isGreaterThan(0);
    }

    @Test
    public void PostRepository_Delete_DeletePost() {
        PostEntity savedPost = postRepository.save(postEntity);
        Assertions.assertThat(savedPost.getId()).isGreaterThan(0);
        postRepository.delete(savedPost);
        Optional<PostEntity> post = postRepository.findById(savedPost.getId());
        Assertions.assertThat(post.isPresent()).isFalse();
    }

    @Test
    public void PostRepository_FindByUser_ReturnList() {
        postRepository.save(postEntity);
        PostEntity postEntity1 = PostEntity.builder()
                .content("post")
                .user(userEntity)
                .build();
        postRepository.save(postEntity1);

        List<PostEntity> posts = postRepository.findByUser(userEntity);
        Assertions.assertThat(posts.size()).isEqualTo(2);
    }
}
