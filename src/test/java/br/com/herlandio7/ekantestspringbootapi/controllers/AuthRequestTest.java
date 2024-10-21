package br.com.herlandio7.ekantestspringbootapi.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthRequestTest {

    @Test
    void testAuthRequestConstructor() {
        AuthRequest request = new AuthRequest("user", "password");
        assertEquals("user", request.getUsername());
        assertEquals("password", request.getPassword());
    }

    @Test
    void testGettersAndSetters() {
        AuthRequest request = new AuthRequest();
        
        request.setUsername("user");
        request.setPassword("password");

        assertEquals("user", request.getUsername());
        assertEquals("password", request.getPassword());
    }
}
