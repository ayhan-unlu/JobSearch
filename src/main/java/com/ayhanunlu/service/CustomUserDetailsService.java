package com.ayhanunlu.service;

import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.Status;
import com.ayhanunlu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
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

/*
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;
*/

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("CUSTOM USER DETAILS WORK");
        Optional<UserEntity> foundUserEntity = userRepository.findByUsername(username);
        if (foundUserEntity.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
//        else {
     /*       if (!authenticationProvider.isUserActive(foundUserEntity)) {
                throw new LockedException(username);
            } else {


            }*/
        UserEntity currentUserEntity = foundUserEntity.get();
        return new User(currentUserEntity.getUsername(), currentUserEntity.getPassword(), true, true, true, true, List.of(new SimpleGrantedAuthority("ROLE_" + foundUserEntity.get().getRole())));

//            return userDetails;
    }
}

