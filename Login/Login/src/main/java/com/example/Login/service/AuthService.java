package com.example.Login.service;

import com.example.Login.dto.RegisterRequest;
import com.example.Login.enums.EnumRole;
import com.example.Login.model.RoleEntity;
import com.example.Login.model.UserEntity;
import com.example.Login.repositories.RoleRepository;
import com.example.Login.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request){
        if(userRepository.findByUsername(request.username()).isPresent()){
            throw new RuntimeException("Nombre de usuario ya existe");
        }
        EnumRole role = EnumRole.valueOf(request.role().toUpperCase());
        RoleEntity userRole = roleRepository.findByRole(EnumRole.USER)
                .orElseGet(() -> roleRepository.save(new RoleEntity(null, EnumRole.USER)));

        UserEntity user = UserEntity.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password())) // Encripta
                .roles(Set.of(userRole))
                .build();

        userRepository.save(user);
    }
}
