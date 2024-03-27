package br.com.herlandio7.ekantestspringbootapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.herlandio7.ekantestspringbootapi.services.AuthService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("auth")
    public String auth(Authentication authentication) {
        return authService.authenticate(authentication);
    }

}
