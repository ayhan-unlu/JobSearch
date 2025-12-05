package com.ayhanunlu.service.impl;

import com.ayhanunlu.data.entity.UserEntity;
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
            userRepository.save(defaultAdminEntity);
        }
    }

}
