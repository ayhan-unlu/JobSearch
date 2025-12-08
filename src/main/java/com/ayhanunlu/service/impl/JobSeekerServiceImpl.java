package com.ayhanunlu.service.impl;

import com.ayhanunlu.data.entity.JobSeekerEntity;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.repository.JobSeekerRepository;
import com.ayhanunlu.repository.UserRepository;
import com.ayhanunlu.service.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public JobSeekerEntity getJobSeekerEntityByUserId(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
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
        return filteredJobSeekerEntities;
    }
}
