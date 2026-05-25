package com.orderms.authservice.service;


import com.orderms.authservice.dto.AuthResponse;
import com.orderms.authservice.dto.LoginRequest;
import com.orderms.authservice.dto.RegisterRequest;
import com.orderms.authservice.entity.Role;
import com.orderms.authservice.entity.User;
import com.orderms.authservice.repository.UserRepository;
import com.orderms.authservice.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void register_ShouldReturnSuccess_WhenEmailDoesNotExist() {
        RegisterRequest request = new RegisterRequest("Saurav", "saurav@gmail.com", "12345");

        when(userRepository.existsByEmail("saurav@gmail.com")).thenReturn(false);
        when(passwordEncoder.encode("12345")).thenReturn("encoded-password");

        AuthResponse response = authService.register(request);

        assertEquals("User registered successfully", response.getMessage());
        assertNull(response.getToken());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_ShouldReturnError_WhenEmailAlreadyExists() {
        RegisterRequest request = new RegisterRequest("Saurav", "saurav@gmail.com", "12345");

        when(userRepository.existsByEmail("saurav@gmail.com")).thenReturn(true);

        AuthResponse response = authService.register(request);

        assertEquals("Email already exists", response.getMessage());
        assertNull(response.getToken());

        verify(userRepository, never()).save(any(User.class));
    }
    @Test
    void login_ShouldReturnToken_WhenCredentialsAreValid() {
        LoginRequest request = new LoginRequest("saurav@gmail.com", "12345");

        User user = User.builder()
                .id(1L)
                .name("Saurav")
                .email("saurav@gmail.com")
                .password("encoded-password")
                .role(Role.USER)
                .build();

        when(userRepository.findByEmail("saurav@gmail.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("12345", "encoded-password")).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("fake-jwt-token");

        AuthResponse response = authService.login(request);

        assertEquals("Login successful", response.getMessage());
        assertEquals("fake-jwt-token", response.getToken());
    }
}
