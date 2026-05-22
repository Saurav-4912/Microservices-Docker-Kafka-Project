package com.orderms.authservice.controller;

import com.orderms.authservice.dto.AuthResponse;
import com.orderms.authservice.dto.LoginRequest;
import com.orderms.authservice.dto.RegisterRequest;
import com.orderms.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}