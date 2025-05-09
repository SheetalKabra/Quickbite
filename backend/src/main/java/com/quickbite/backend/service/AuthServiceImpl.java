package com.quickbite.backend.service;

import com.quickbite.backend.dto.LoginBy;
import com.quickbite.backend.dto.LoginRequestDto;
import com.quickbite.backend.dto.LoginResponseDto;
import com.quickbite.backend.dto.RegisterRequestDto;
import com.quickbite.backend.model.Role;
import com.quickbite.backend.model.User;
import com.quickbite.backend.repository.AuthRepository;
import com.quickbite.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
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
        user.setRole(Objects.equals(registerRequestDto.getRole(), "USER") ? Role.USER : Role.ADMIN);
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        authRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public LoginResponseDto authenticate(LoginRequestDto loginRequestDto) {
        LoginBy loginBy = loginRequestDto.getLoginBy();
        User user;
        if(loginBy.equals(LoginBy.EMAIL)){
            user = authRepository.findByEmail(loginRequestDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("User doesn't exists!!!"));
        } else {
            System.out.println("here Mobile");
            user = authRepository.findByMobileNo(loginRequestDto.getMobile())
                    .orElseThrow(() -> new RuntimeException("User doesn't exists!!!"));
        }
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new RuntimeException("Incorrect password!!!");
        }
        String token = jwtUtil.generateToken(loginRequestDto);
        return new LoginResponseDto(token);
    }
}
