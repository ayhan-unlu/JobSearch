package com.ayhanunlu.service.impl;

import com.ayhanunlu.data.dto.LoginDto;
import com.ayhanunlu.data.dto.LoginResult;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.LoginResponse;
import com.ayhanunlu.enums.Status;
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

  /*  @Override
    public LoginResult login(LoginDto loginDto) {
        System.out.println("Custom Login method from authentication service implementation works !!!!!");
        Optional<UserEntity> foundUserEntity = userRepository.findByUsername(loginDto.getUsername());
        LoginResult loginResult = new LoginResult(LoginResponse.FAIL, null);
        if (!isUsernameValidated(foundUserEntity)) {
            loginResult.setLoginResponse(LoginResponse.NO_SUCH_USER);
        } else {
            if (!isPasswordValidated(loginDto, foundUserEntity)) {
                System.out.println("Found UserEntity failed attempts" + foundUserEntity.get().getFailedLoginAttempts());
                increaseFailedLoginAttemptsOne(foundUserEntity);
                loginResult.setLoginResponse(LoginResponse.FAIL);
            } else {
                loginResult.setLoginResponse(LoginResponse.SUCCESS);
                loginResult.setUserEntity(foundUserEntity.get());
            }
        }
        return loginResult;
    }
*/
    private boolean isUsernameValidated(Optional<UserEntity> foundUserEntity) {
        return foundUserEntity.isPresent();
    }

    private boolean isPasswordValidated(LoginDto loginDto, Optional<UserEntity> foundUserEntity) {
        return passwordEncoder.matches(loginDto.getPassword(), foundUserEntity.get().getPassword());
    }

    private void blockUserEntity(Optional<UserEntity> foundUserEntity) {
        userRepository.findByUsername(foundUserEntity.get().getUsername()).get().setStatus(Status.BLOCKED);
        userRepository.save(foundUserEntity.get());
    }

    private void increaseFailedLoginAttemptsOne(Optional<UserEntity> foundUserEntity) {
        UserEntity currentUserEntity = foundUserEntity.get();
        int currentFailedLoginAttempts = currentUserEntity.getFailedLoginAttempts();
        System.out.println("Current failed attempts " + currentFailedLoginAttempts);
        currentUserEntity.setFailedLoginAttempts(currentFailedLoginAttempts + 1);
        userRepository.save(currentUserEntity);
        System.out.println("Increased failedAttempts " + foundUserEntity.get().getFailedLoginAttempts());
    }
}