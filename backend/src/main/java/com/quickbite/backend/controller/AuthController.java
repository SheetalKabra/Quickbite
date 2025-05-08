package com.quickbite.backend.controller;

import com.quickbite.backend.dto.RegisterRequestDto;
import com.quickbite.backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDto registerRequestDto){
        String message = authService.registerUser(registerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
