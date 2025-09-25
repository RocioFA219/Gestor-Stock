package com.example.Login.controller;

import com.example.Login.dto.LoginRequest;
import com.example.Login.dto.RegisterRequest;
import com.example.Login.dto.UserInfoResponse;
import com.example.Login.model.CustomUserDetails;
import com.example.Login.model.UserEntity;
import com.example.Login.repositories.UserRepository;
import com.example.Login.service.AuthService;
import com.example.Login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Credenciales invalidas.");
        }
        UserEntity user = userRepository.findByUsername(request.username()).orElse(null);
        List<String> roles = user.getRoles().stream()
                .map(r->r.getRole().name())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new UserInfoResponse(user.getUsername(),user.getEmail(),roles));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        authService.register(request);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication){
        if(authentication==null || !authentication.isAuthenticated()){
            return ResponseEntity.status(401).body("Usuario no autenticado");
        }
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(Map.of(
                "id",userDetails.getId(),
                "username",userDetails.getUsername(),
                "email", userDetails.getEmail()
        ));
    }
}
