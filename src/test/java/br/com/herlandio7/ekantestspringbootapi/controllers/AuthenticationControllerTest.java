package br.com.herlandio7.ekantestspringbootapi.controllers;

import br.com.herlandio7.ekantestspringbootapi.services.JwtService;
import br.com.herlandio7.ekantestspringbootapi.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticate_SuccessfulAuthentication() {
        AuthRequest request = new AuthRequest("username", "password");
        UserDetails userDetails = mock(UserDetails.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);
        when(userService.loadUserByUsername("username")).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn("mockedToken");

        ResponseEntity<?> response = authenticationController.authenticate(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("mockedToken", ((AuthResponse) response.getBody()).getToken());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userService, times(1)).loadUserByUsername("username");
        verify(jwtService, times(1)).generateToken(userDetails);
    }

    @Test
    void testAuthenticate_InvalidCredentials() {
        AuthRequest request = new AuthRequest("username", "wrongpassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        ResponseEntity<?> response = authenticationController.authenticate(request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Usuário ou senha inválidos", response.getBody());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userService, never()).loadUserByUsername(anyString());
        verify(jwtService, never()).generateToken(any(UserDetails.class));
    }

    @Test
    void testAuthenticate_UserNotFound() {
        
        AuthRequest request = new AuthRequest("username", "password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);
        when(userService.loadUserByUsername("username")).thenReturn(null);

        ResponseEntity<?> response = authenticationController.authenticate(request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Usuário ou senha inválidos", response.getBody());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userService, times(1)).loadUserByUsername("username");
        verify(jwtService, never()).generateToken(any(UserDetails.class));
    }

    @Test
    void testAuthenticate_InternalServerError() {
    
        AuthRequest request = new AuthRequest("username", "password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<?> response = authenticationController.authenticate(request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao autenticar", response.getBody());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userService, never()).loadUserByUsername(anyString());
        verify(jwtService, never()).generateToken(any(UserDetails.class));
    }
}
