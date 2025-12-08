package com.ayhanunlu.service;

import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.Status;
import com.ayhanunlu.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();
        Optional<UserEntity> foundUserEntity = userRepository.findByUsername(username);
        if (foundUserEntity.isEmpty()) {
            throw new UsernameNotFoundException(username);
        } else {
            if (!isUserActive(foundUserEntity)) {
                throw new LockedException(username);
            } else {
                if (passwordEncoder.matches(rawPassword, foundUserEntity.get().getPassword())) {
                    resetFailedLoginAttemptsToZero(foundUserEntity);
                    UserDetails userDetails = new User(
                            foundUserEntity.get().getUsername(),
                            foundUserEntity.get().getPassword(),
                            List.of(new SimpleGrantedAuthority("ROLE_" + foundUserEntity.get().getRole()))
                    );
                    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                } else {
                    increaseFailedLoginAttemptsOne(foundUserEntity);
                    if (foundUserEntity.get().getFailedLoginAttempts() >= 3) {
                        blockUser(foundUserEntity);
                        throw new LockedException(username);
                    } else {
                        throw new BadCredentialsException(username);
                    }
                }
            }

        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public boolean isUserActive(Optional<UserEntity> foundUserEntity) {
        return foundUserEntity.get().getStatus() == Status.ACTIVE;
    }

    public boolean isUsersFailedLoginAttemptsThree(Optional<UserEntity> foundUserEntity) {
        return foundUserEntity.get().getFailedLoginAttempts() >= 3;
    }

    public void increaseFailedLoginAttemptsOne(Optional<UserEntity> foundUserEntity) {
        UserEntity updatedUserEntity = foundUserEntity.get();
        updatedUserEntity.setFailedLoginAttempts(foundUserEntity.get().getFailedLoginAttempts() + 1);
        userRepository.save(updatedUserEntity);
    }

    public void blockUser(Optional<UserEntity> foundUserEntity) {
        UserEntity updatedUserEntity = foundUserEntity.get();
        updatedUserEntity.setStatus(Status.BLOCKED);
        userRepository.save(updatedUserEntity);
    }

    public void resetFailedLoginAttemptsToZero(Optional<UserEntity> foundUserEntity) {
        UserEntity updatedUserEntity = foundUserEntity.get();
        updatedUserEntity.setFailedLoginAttempts(0);
        userRepository.save(updatedUserEntity);
    }
}
