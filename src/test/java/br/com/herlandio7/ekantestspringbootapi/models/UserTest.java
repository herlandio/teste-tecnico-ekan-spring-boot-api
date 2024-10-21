package br.com.herlandio7.ekantestspringbootapi.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(null, user.getId());
        assertEquals(null, user.getUsername());
        assertEquals(null, user.getPassword());
        assertEquals(null, user.getRole());
    }

    @Test
    void testParameterizedConstructor() {
        User userWithParams = new User();
        userWithParams.setId(1L);
        userWithParams.setUsername("testuser");
        userWithParams.setPassword("testpass");
        userWithParams.setRole("ROLE_USER");

        assertEquals(1L, userWithParams.getId());
        assertEquals("testuser", userWithParams.getUsername());
        assertEquals("testpass", userWithParams.getPassword());
        assertEquals("ROLE_USER", userWithParams.getRole());
    }

    @Test
    void testSettersAndGetters() {
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("testpass");
        user.setRole("ROLE_USER");

        assertEquals(1L, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("testpass", user.getPassword());
        assertEquals("ROLE_USER", user.getRole());
    }

    @Test
    void testUserRole() {
        user.setRole("ADMIN");
        assertEquals("ADMIN", user.getRole());
    }
}
