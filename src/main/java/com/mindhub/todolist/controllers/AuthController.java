package com.mindhub.todolist.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindhub.todolist.configuration.JwtUtils;
import com.mindhub.todolist.dtos.UserDto;
import com.mindhub.todolist.dtos.UserLoginDto;
import com.mindhub.todolist.dtos.response.TokenResponse;
import com.mindhub.todolist.dtos.response.UserResponse;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.services.interfaces.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Authentications and registrations users")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private JwtUtils jwtUtil;
    private AuthenticationManager authenticationManager;
    private IUserService userService;
    private ObjectMapper objectMapper;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto registrationDto) {
        UserEntity userRegister = userService.registerUser(registrationDto);
        return ResponseEntity.ok(UserResponse.toResponse(userRegister));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateClaims(authentication.getName(), authentication.getAuthorities());
        return ResponseEntity.ok(new TokenResponse(jwt));
    }
}
