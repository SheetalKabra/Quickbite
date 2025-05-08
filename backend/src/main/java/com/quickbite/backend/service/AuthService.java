package com.quickbite.backend.service;

import com.quickbite.backend.dto.RegisterRequestDto;

public interface AuthService {

    String registerUser(RegisterRequestDto registerRequestDto);
}
