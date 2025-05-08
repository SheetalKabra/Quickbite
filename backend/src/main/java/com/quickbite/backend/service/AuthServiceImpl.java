package com.quickbite.backend.service;

import com.quickbite.backend.dto.RegisterRequestDto;
import com.quickbite.backend.model.Role;
import com.quickbite.backend.model.User;
import com.quickbite.backend.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String registerUser(RegisterRequestDto registerRequestDto) {
        Optional<User> existingUser = authRepository.findByEmailOrMobileNo(registerRequestDto.getEmail(), registerRequestDto.getMobile());
        if(existingUser.isPresent()){
            throw new RuntimeException("User already exists!!!");
        }
        User user = new User();
        user.setFirstName(registerRequestDto.getFirstName());
        user.setLastName(registerRequestDto.getLastName());
        user.setEmail(registerRequestDto.getEmail());
        user.setMobileNo(registerRequestDto.getMobile());
        user.setRole(Objects.equals(registerRequestDto.getRole(), "User") ? Role.USER : Role.ADMIN);
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        authRepository.save(user);
        return "User registered successfully";
    }
}
