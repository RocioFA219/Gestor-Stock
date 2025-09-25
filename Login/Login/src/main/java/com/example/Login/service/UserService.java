package com.example.Login.service;

import com.example.Login.model.UserEntity;
import com.example.Login.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<UserEntity> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public Optional<UserEntity> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
