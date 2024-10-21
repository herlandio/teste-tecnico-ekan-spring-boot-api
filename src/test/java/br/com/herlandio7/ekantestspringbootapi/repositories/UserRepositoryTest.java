package br.com.herlandio7.ekantestspringbootapi.repositories;

import br.com.herlandio7.ekantestspringbootapi.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setUsername("testUser");
        testUser.setPassword("password");
        userRepository.save(testUser);
    }

    @Test
    void testFindByUsername() {
        Optional<User> foundUser = userRepository.findByUsername("testUser");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("testUser");
    }

    @Test
    void testFindByUsernameNotFound() {
        Optional<User> foundUser = userRepository.findByUsername("nonExistentUser");

        assertThat(foundUser).isNotPresent();
    }
}
