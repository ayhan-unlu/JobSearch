package com.ayhanunlu.service.impl;

import com.ayhanunlu.data.dto.SessionDto;
import com.ayhanunlu.data.dto.RegisterDto;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.Role;
import com.ayhanunlu.repository.UserRepository;
import com.ayhanunlu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    @Override
    public void createDefaultAdmin() {
        if (userRepository.findByUsername("a").isEmpty()) {
            UserEntity defaultAdminEntity = new UserEntity();
            defaultAdminEntity.setUsername("a");
            defaultAdminEntity.setPassword(passwordEncoder.encode("a"));
            defaultAdminEntity.setRole(Role.ADMIN);
            userRepository.save(defaultAdminEntity);
        }
    }

    @Override
    public SessionDto getCurrentAdmin(UserEntity userEntity) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(userEntity.getId());
        sessionDto.setUsername(userEntity.getUsername());
        sessionDto.setRole(userEntity.getRole());

        return sessionDto;
    }

    @Override
    public boolean registerNewUser(RegisterDto registerDto) {

        if(!isUsernameAlreadyInUse(registerDto)){

        UserEntity registeredUserEntity = new UserEntity();
        registeredUserEntity.setUsername(registerDto.getUsername());
        registeredUserEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        registeredUserEntity.setRole(Role.USER);

        userRepository.save(registeredUserEntity);
        return true;
        }

        return false;
    }

    private boolean isUsernameAlreadyInUse(RegisterDto registerDto){
        return userRepository.findByUsername(registerDto.getUsername()).isPresent();
    }

}
