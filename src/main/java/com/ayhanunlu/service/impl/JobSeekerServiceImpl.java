package com.ayhanunlu.service.impl;

import com.ayhanunlu.data.dto.RegisterDto;
import com.ayhanunlu.data.entity.JobSeekerEntity;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.repository.JobSeekerRepository;
import com.ayhanunlu.repository.UserRepository;
import com.ayhanunlu.service.JobSeekerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JobSeekerServiceImpl implements JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void registerNewJobSeeker(UserEntity registeredUserEntity,RegisterDto registerDto){
        JobSeekerEntity jobSeekerEntity = new JobSeekerEntity();
        jobSeekerEntity.setUserEntity(registeredUserEntity);
        jobSeekerEntity.setName(registerDto.getName());
        jobSeekerEntity.setSurname(registerDto.getSurname());
        jobSeekerEntity.setPhone(registerDto.getPhone());
        jobSeekerEntity.setFieldOfExperience(registerDto.getFieldOfExperience());
        jobSeekerEntity.setExperienceYear(registerDto.getExperienceYear());
        jobSeekerEntity.setMilitaryServiceFinished(registerDto.isMilitaryServiceFinished());
        jobSeekerRepository.save(jobSeekerEntity);
        log.info("Job Seeker {} Details are registered", jobSeekerEntity.getUserEntity().getUsername());
    }
    @Override
    public JobSeekerEntity getJobSeekerEntityByUserId(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        log.info("Job Seeker entity fetched by user id {}",userId);
        return jobSeekerRepository.findByUserEntity(userEntity);
    }

    @Override
    public List<JobSeekerEntity> getAllJobSeekerEntities() {
        return jobSeekerRepository.findAll();
    }

    @Override
    public List<JobSeekerEntity> getJobSeekerEntitiesFinishedMilitaryService() {
        List<JobSeekerEntity> filteredJobSeekerEntities = new ArrayList<>();
        for (JobSeekerEntity jobSeekerEntity : jobSeekerRepository.findAll()) {
            if (jobSeekerEntity.isMilitaryServiceFinished()) {
                filteredJobSeekerEntities.add(jobSeekerEntity);
            }
        }
        log.info("All Job Seekers finished Military Service are filtered Successfully");
        return filteredJobSeekerEntities;
    }

    @Override
    public List<JobSeekerEntity> getJobSeekerEntitiesHaveExperience5PlusYears() {
        List<JobSeekerEntity> filteredJobSeekerEntities = new ArrayList<>();
        for (JobSeekerEntity jobSeekerEntity : jobSeekerRepository.findAll()) {
            if (jobSeekerEntity.getExperienceYear() >= 5) {
                filteredJobSeekerEntities.add(jobSeekerEntity);
            }
        }
        log.info("All Job Seekers with 5+ experience year are filtered Successfully");
        return filteredJobSeekerEntities;
    }
}
