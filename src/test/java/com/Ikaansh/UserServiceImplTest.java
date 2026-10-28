package com.Ikaansh;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.security.authentication.BadCredentialsException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Ikaansh.entity.User;
import com.Ikaansh.repository.UserRepository;
import com.Ikaansh.security.JwtService;
import com.Ikaansh.service.UserServiceImpl;

	

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void loginUserShouldReturnTokenForValidCredentials() {

        User user = new User();
        user.setName("Abhishek");
        user.setEmail("test@example.com");
        user.setPassword("hashedPassword");

        when(userRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches(
                "Abhi@12345", "hashedPassword"))
                .thenReturn(true);

        when(jwtService.generateToken("test@example.com"))
                .thenReturn("mock-jwt-token");

        String token = userService.loginUser(
                "test@example.com", "Abhi@12345");

        assertEquals("mock-jwt-token", token);
    }
    



@Test
void loginUserShouldRejectInvalidPassword() {

    User user = new User();
    user.setName("Abhishek");
    user.setEmail("test@example.com");
    user.setPassword("hashedPassword");

    when(userRepository.findByEmail("test@example.com"))
            .thenReturn(Optional.of(user));

    when(passwordEncoder.matches(
            "WrongPassword", "hashedPassword"))
            .thenReturn(false);

    assertThrows(
            IllegalArgumentException.class,
            () -> userService.loginUser(
                    "test@example.com", "WrongPassword"));
}
}
