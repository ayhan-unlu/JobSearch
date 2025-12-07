package com.ayhanunlu.service;

import com.ayhanunlu.data.dto.AdminSessionDto;
import com.ayhanunlu.data.dto.RegisterDto;
import com.ayhanunlu.data.entity.UserEntity;

public interface UserService {
    void createDefaultAdmin();
    AdminSessionDto getCurrentAdmin(UserEntity userEntity);
    boolean registerNewUser(RegisterDto registerDto);
}
