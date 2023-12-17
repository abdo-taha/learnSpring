package com.abdo.learn.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.abdo.learn.model.entity.UserEntity;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private UserEntity userEntity;

    @BeforeEach
    public void init() {
        userEntity = UserEntity.builder()
                .email("test@mail.com")
                .password("password")
                .name("user name")
                .build();
    }

    @Test
    public void UserRepository_Save_ReturnSavedUser() {
        UserEntity savedUser = userRepository.save(userEntity);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepository_FindByEmail_ReturnUser() {
        userRepository.save(userEntity);
        Optional<UserEntity> user1 = userRepository.findByEmail(userEntity.getEmail());
        Optional<UserEntity> user2 = userRepository.findByEmail("wrong@mail.com");

        Assertions.assertThat(user1.isPresent()).isTrue();
        Assertions.assertThat(user1.get().getId()).isGreaterThan(0);
        Assertions.assertThat(user2.isPresent()).isFalse();
    }

    @Test
    public void UserRepository_Delete_DeleteUser() {

        UserEntity savedUser = userRepository.save(userEntity);
        userRepository.delete(savedUser);
        Optional<UserEntity> user = userRepository.findById(savedUser.getId());

        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
        Assertions.assertThat(user.isPresent()).isFalse();
    }
}
