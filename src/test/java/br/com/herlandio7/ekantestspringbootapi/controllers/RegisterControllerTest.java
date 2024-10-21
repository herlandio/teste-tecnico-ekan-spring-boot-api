package br.com.herlandio7.ekantestspringbootapi.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.herlandio7.ekantestspringbootapi.models.User;
import br.com.herlandio7.ekantestspringbootapi.repositories.UserRepository;

class RegisterControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RegisterController registerController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }

    @Test
    void testRegisterUser_Success() throws Exception {

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("testpass")).thenReturn("encodedPassword");

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\", \"password\":\"testpass\"}"))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("Usuário registrado com sucesso!", result.getResponse().getContentAsString()));

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_UserAlreadyExists() throws Exception {

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(new User()));

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\", \"password\":\"testpass\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertEquals("Usuário já existe!", result.getResponse().getContentAsString()));

        verify(userRepository, times(0)).save(any(User.class));
    }

    @ParameterizedTest
    @CsvSource({
        "null, testpass, Username and password cannot be null or empty",
        "testuser, null, Username and password cannot be null or empty",
        "'', testpass, Username and password cannot be null or empty",
        "testuser, '', Username and password cannot be null or empty"
    })
    void testRegisterUser_InvalidInputs(String username, String password, String expectedMessage) throws Exception {
        String jsonContent = String.format("{\"username\":%s, \"password\":%s}", 
                                            username.equals("null") ? "null" : "\"" + username + "\"", 
                                            password.equals("null") ? "null" : "\"" + password + "\"");

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertEquals(expectedMessage, result.getResponse().getContentAsString()));

        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void testRegisterUser_EmptyPassword() throws Exception {

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\", \"password\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertEquals("Username and password cannot be null or empty", result.getResponse().getContentAsString()));

        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void testRegisterUser_EmptyUsername() throws Exception {
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"\", \"password\":\"testpass\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertEquals("Username and password cannot be null or empty", result.getResponse().getContentAsString()));

        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void testRegisterUser_InvalidJSON() throws Exception {
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"invalidField\":\"testuser\", \"password\":\"testpass\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());

        verify(userRepository, times(0)).save(any(User.class));
    }
}
