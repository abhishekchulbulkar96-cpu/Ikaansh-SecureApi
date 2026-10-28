package com.Ikaansh.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Ikaansh.dto.LoginRequest;
import com.Ikaansh.dto.LoginResponse;
import com.Ikaansh.dto.RegisterRequest;
import com.Ikaansh.dto.UserResponse;
import com.Ikaansh.entity.User;
import com.Ikaansh.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody RegisterRequest request) {

        User user = userService.registerUser(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    

    @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(
        @Valid @RequestBody LoginRequest request) {

    String token = userService.loginUser(
            request.getEmail(),
            request.getPassword());

    LoginResponse response = new LoginResponse(token, "Bearer");

    return ResponseEntity.ok(response);
}
    

@GetMapping("/me")
public ResponseEntity<String> getCurrentUser(
        org.springframework.security.core.Authentication authentication) {

    return ResponseEntity.ok(
            "Authenticated user: " + authentication.getName());
}


}
