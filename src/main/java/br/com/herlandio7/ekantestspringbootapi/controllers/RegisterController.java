package br.com.herlandio7.ekantestspringbootapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.herlandio7.ekantestspringbootapi.models.User;
import br.com.herlandio7.ekantestspringbootapi.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Register endpoint")
@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Register user")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {

        if (request.getUsername() == null || request.getUsername().isEmpty() || 
            request.getPassword() == null || request.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Username and password cannot be null or empty");
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe!");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole("ROLE_USER");

        userRepository.save(newUser);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }
}
