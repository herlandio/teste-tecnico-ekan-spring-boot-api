package br.com.herlandio7.ekantestspringbootapi.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class RegisterRequestTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidRegisterRequest() {
        RegisterRequest request = new RegisterRequest("testuser", "testpass");
        
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);
        assertEquals(0, violations.size(), "Expected no validation errors");
    }

    @Test
    void testRegisterRequest_EmptyUsername() {
        RegisterRequest request = new RegisterRequest("", "testpass");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size(), "Expected one validation error for username");
        assertEquals("Username is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    void testRegisterRequest_EmptyPassword() {
        RegisterRequest request = new RegisterRequest("testuser", "");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size(), "Expected one validation error for password");
        assertEquals("Password is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    void testRegisterRequest_NullUsername() {
        RegisterRequest request = new RegisterRequest(null, "testpass");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size(), "Expected one validation error for username");
        assertEquals("Username is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    void testRegisterRequest_NullPassword() {
        RegisterRequest request = new RegisterRequest("testuser", null);

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size(), "Expected one validation error for password");
        assertEquals("Password is mandatory", violations.iterator().next().getMessage());
    }
}

