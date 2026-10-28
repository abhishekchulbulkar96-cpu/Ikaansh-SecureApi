package com.Ikaansh.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.Ikaansh.dto.UserResponse;
import com.Ikaansh.entity.User;
import com.Ikaansh.repository.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private final UserRepository userRepository;

    public UserProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyProfile(
            Authentication authentication) {

        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(() ->
                    new IllegalArgumentException("User not found"));

        return ResponseEntity.ok(
                new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getCreatedAt()));
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateMyProfile(
    		
            Authentication authentication, @Valid @RequestBody UpdateProfileRequest request) {

        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(() ->
                    new IllegalArgumentException("User not found"));

        user.setName(request.getName());

        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(
                new UserResponse(
                        updatedUser.getId(),
                        updatedUser.getName(),
                        updatedUser.getEmail(),
                        updatedUser.getCreatedAt()));
    }
    
    

    public static class UpdateProfileRequest {

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    
    

@DeleteMapping("/me")
public ResponseEntity<Void> deleteMyAccount(
        Authentication authentication) {

    User user = userRepository
            .findByEmail(authentication.getName())
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

    userRepository.delete(user);

    return ResponseEntity.noContent().build();
}

}

