package com.ayhanunlu.service.impl;

import com.ayhanunlu.data.dto.SessionDto;
import com.ayhanunlu.data.dto.RegisterDto;
import com.ayhanunlu.data.entity.JobSeekerEntity;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.Role;
import com.ayhanunlu.enums.Status;
import com.ayhanunlu.repository.JobSeekerRepository;
import com.ayhanunlu.repository.UserRepository;
import com.ayhanunlu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    @Autowired
    private JobSeekerServiceImpl jobSeekerService;


    @Override
    public void createDefaultAdmin() {
        if (userRepository.findByUsername("a").isEmpty()) {
            UserEntity defaultAdminEntity = new UserEntity();
            defaultAdminEntity.setUsername("a");
            defaultAdminEntity.setPassword(passwordEncoder.encode("a"));
            defaultAdminEntity.setRole(Role.ADMIN);
            defaultAdminEntity.setStatus(Status.ACTIVE);
            userRepository.save(defaultAdminEntity);
            log.info("Default Admin Created");
        }
    }

    @Override
    public SessionDto getCurrentAdmin(UserEntity userEntity) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(userEntity.getId());
        sessionDto.setUsername(userEntity.getUsername());
        sessionDto.setRole(userEntity.getRole());
        log.info("Current Admin {} added as session Dto", userEntity.getUsername());
        return sessionDto;
    }

    @Override
    public boolean registerNewUser(RegisterDto registerDto) {

        if (!isUsernameAlreadyInUse(registerDto)) {

            UserEntity registeredUserEntity = new UserEntity();
            registeredUserEntity.setUsername(registerDto.getUsername());
            registeredUserEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            registeredUserEntity.setRole(Role.USER);
            registeredUserEntity.setStatus(Status.ACTIVE);
            registeredUserEntity.setFailedLoginAttempts(0);
            userRepository.save(registeredUserEntity);
            jobSeekerService.registerNewJobSeeker(registeredUserEntity, registerDto);
/*
            JobSeekerEntity registeredJobSeekerEntity = new JobSeekerEntity();
            registeredJobSeekerEntity.setUserEntity(registeredUserEntity);
            registeredJobSeekerEntity.setName(registerDto.getName());
            registeredJobSeekerEntity.setSurname(registerDto.getSurname());
            registeredJobSeekerEntity.setPhone(registerDto.getPhone());
            registeredJobSeekerEntity.setFieldOfExperience(registerDto.getFieldOfExperience());
            registeredJobSeekerEntity.setExperienceYear(registerDto.getExperienceYear());
            registeredJobSeekerEntity.setMilitaryServiceFinished(registerDto.isMilitaryServiceFinished());
*/

//            jobSeekerRepository.save(registeredJobSeekerEntity);
            log.info("New User {} added as jobSeeker", registeredUserEntity.getUsername());
            return true;
        }
        log.error("Registration of new User {} Failed,User already exists", registerDto.getUsername());
        return false;
    }

    private boolean isUsernameAlreadyInUse(RegisterDto registerDto) {
        return userRepository.findByUsername(registerDto.getUsername()).isPresent();
    }

    //    @Override
    public SessionDto createSessionDto(UserEntity userEntity) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(userEntity.getId());
        sessionDto.setUsername(userEntity.getUsername());
        sessionDto.setRole(userEntity.getRole());
        sessionDto.setStatus(userEntity.getStatus());
        sessionDto.setFailedLoginAttempts(userEntity.getFailedLoginAttempts());
        log.info("Session Dto {} created",sessionDto.getUsername());
        return sessionDto;
    }
}
