package br.com.herlandio7.ekantestspringbootapi.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AuthResponseTest {

    @Test
    void testAuthResponse() {
        String token = "sample_token";
        AuthResponse authResponse = new AuthResponse(token);

        assertEquals(token, authResponse.getToken(), "O token retornado não corresponde ao token fornecido.");
    }
}

