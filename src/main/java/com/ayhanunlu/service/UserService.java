package com.ayhanunlu.service;

import com.ayhanunlu.data.dto.SessionDto;
import com.ayhanunlu.data.dto.RegisterDto;
import com.ayhanunlu.data.entity.UserEntity;

public interface UserService {
    void createDefaultAdmin();

    SessionDto getCurrentAdmin(UserEntity userEntity);

    boolean registerNewUser(RegisterDto registerDto);

//    SessionDto createSessionDto(UserEntity userEntity);
}
