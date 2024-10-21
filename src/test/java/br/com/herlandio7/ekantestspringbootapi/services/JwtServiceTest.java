package br.com.herlandio7.ekantestspringbootapi.services;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Method;
import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
    }

    @Test
    void testGenerateToken() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("testuser");

        String token = jwtService.generateToken(userDetails);

        assertNotNull(token);
        assertTrue(token.startsWith("ey"));
    }

    @Test
    void testExtractUsername() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("testuser");

        String token = jwtService.generateToken(userDetails);
        String username = jwtService.extractUsername(token);

        assertEquals("testuser", username);
    }

    @Test
    void testIsTokenValid_ValidToken() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("testuser");

        String token = jwtService.generateToken(userDetails);
        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertTrue(isValid);
    }

    @Test
    void testIsTokenExpired_ValidToken() throws Exception {
        String token = Jwts.builder()
                .setSubject("testuser")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(getSigningKey())
                .compact();

        assertFalse(isTokenExpired(token));
    }

    private Key getSigningKey() throws Exception {
        Method method = JwtService.class.getDeclaredMethod("getSigningKey");
        method.setAccessible(true);
        return (Key) method.invoke(jwtService);
    }

    private boolean isTokenExpired(String token) throws Exception {
        Method method = JwtService.class.getDeclaredMethod("isTokenExpired", String.class);
        method.setAccessible(true);
        return (boolean) method.invoke(jwtService, token);
    }
}
