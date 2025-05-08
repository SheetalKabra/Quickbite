package com.quickbite.backend.service;

import com.quickbite.backend.dto.LoginRequestDto;
import com.quickbite.backend.dto.LoginResponseDto;
import com.quickbite.backend.dto.RegisterRequestDto;

public interface AuthService {

    String registerUser(RegisterRequestDto registerRequestDto);

    LoginResponseDto authenticate(LoginRequestDto loginRequestDto);
}
