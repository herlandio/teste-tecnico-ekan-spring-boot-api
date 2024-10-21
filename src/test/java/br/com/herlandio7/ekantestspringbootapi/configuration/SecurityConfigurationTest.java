package br.com.herlandio7.ekantestspringbootapi.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

@SpringBootTest
class SecurityConfigurationTest {

    private SecurityConfiguration securityConfiguration;
    private UserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        
        userDetailsService = Mockito.mock(UserDetailsService.class);
        securityConfiguration = new SecurityConfiguration(userDetailsService) {
            @Override
            public JwtAuthenticationFilter jwtRequestFilter() {
                return new JwtAuthenticationFilter();
            }
        };
    }

    @Test
    void passwordEncoder_ShouldReturnBCryptPasswordEncoder() {
        PasswordEncoder passwordEncoder = securityConfiguration.passwordEncoder();
        assertNotNull(passwordEncoder);
        assertTrue(passwordEncoder instanceof BCryptPasswordEncoder);
    }

    @Test
    void authenticationManager_ShouldReturnAuthenticationManager() throws Exception {
        AuthenticationConfiguration authenticationConfiguration = mock(AuthenticationConfiguration.class);
        AuthenticationManager authenticationManagerMock = mock(AuthenticationManager.class);
        when(authenticationConfiguration.getAuthenticationManager()).thenReturn(authenticationManagerMock);

        AuthenticationManager authenticationManager = securityConfiguration.authenticationManager(authenticationConfiguration);
        assertNotNull(authenticationManager);
        assertEquals(authenticationManagerMock, authenticationManager);
    }

    @Test
    void jwtRequestFilter_ShouldReturnJwtAuthenticationFilter() {
        JwtAuthenticationFilter jwtAuthenticationFilter = securityConfiguration.jwtRequestFilter();
        assertNotNull(jwtAuthenticationFilter);
        assertTrue(jwtAuthenticationFilter instanceof JwtAuthenticationFilter);
    }

    @Test
    void authenticationProvider_ShouldReturnDaoAuthenticationProvider() throws Exception {
        AuthenticationProvider authenticationProvider = securityConfiguration.authenticationProvider();
        assertNotNull(authenticationProvider);
        assertTrue(authenticationProvider instanceof DaoAuthenticationProvider);

        DaoAuthenticationProvider daoAuthProvider = (DaoAuthenticationProvider) authenticationProvider;

        Field userDetailsServiceField = DaoAuthenticationProvider.class.getDeclaredField("userDetailsService");
        userDetailsServiceField.setAccessible(true);
        UserDetailsService injectedUserDetailsService = (UserDetailsService) userDetailsServiceField.get(daoAuthProvider);

        assertEquals(userDetailsService, injectedUserDetailsService);

        Field passwordEncoderField = DaoAuthenticationProvider.class.getDeclaredField("passwordEncoder");
        passwordEncoderField.setAccessible(true);
        PasswordEncoder injectedPasswordEncoder = (PasswordEncoder) passwordEncoderField.get(daoAuthProvider);

        assertTrue(injectedPasswordEncoder instanceof BCryptPasswordEncoder);
    }
}
