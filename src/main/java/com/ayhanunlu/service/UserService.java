package com.ayhanunlu.service;

import com.ayhanunlu.data.dto.SessionDto;
import com.ayhanunlu.data.dto.RegisterDto;
import com.ayhanunlu.data.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    void createDefaultAdmin();

    SessionDto getCurrentAdmin(UserEntity userEntity);

    boolean registerNewUser(RegisterDto registerDto);

    int getCurrentFailedLoginAttemptCount(Optional<UserEntity> userEntity);
//    SessionDto createSessionDto(UserEntity userEntity);
}
