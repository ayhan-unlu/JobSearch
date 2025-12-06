package com.ayhanunlu.service;

import com.ayhanunlu.data.dto.LoginDto;
import com.ayhanunlu.data.dto.LoginResult;

public interface AuthenticationService {
    LoginResult login(LoginDto loginDto);
}
