package com.ayhanunlu.service.impl;

import com.ayhanunlu.data.dto.LoginDto;
import com.ayhanunlu.data.dto.LoginResult;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.LoginResponse;
import com.ayhanunlu.repository.UserRepository;
import com.ayhanunlu.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public LoginResult login(LoginDto loginDto) {

        Optional<UserEntity> foundUserEntity = userRepository.findByUsername(loginDto.getUsername());
        LoginResult loginResult = new LoginResult(LoginResponse.FAIL, null);
        if (!isUsernameValidated(foundUserEntity)) {
            loginResult.setLoginResponse(LoginResponse.NO_SUCH_USER);
        } else {
            if (!isPasswordValidated(loginDto, foundUserEntity)) {
                loginResult.setLoginResponse(LoginResponse.FAIL);
            }else {
                loginResult.setLoginResponse(LoginResponse.SUCCESS);
                loginResult.setUserEntity(foundUserEntity.get());
            }
        }
        return loginResult;
    }

    private boolean isUsernameValidated(Optional<UserEntity> foundUserEntity) {
        return foundUserEntity.isPresent();
    }

    private boolean isPasswordValidated(LoginDto loginDto, Optional<UserEntity> foundUserEntity) {
        return passwordEncoder.matches(loginDto.getPassword(), foundUserEntity.get().getPassword());
    }
}