package com.ayhanunlu.service;

import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> foundUserEntity = userRepository.findByUsername(username);
        if (foundUserEntity.isEmpty()) {
            throw new UsernameNotFoundException(username);
        } else {
            UserDetails userDetails = new User(foundUserEntity.get().getUsername(), foundUserEntity.get().getPassword(), true, true, true, true, List.of(new SimpleGrantedAuthority("ROLE_"+foundUserEntity.get().getRole())));

            return userDetails;
        }
    }
}
