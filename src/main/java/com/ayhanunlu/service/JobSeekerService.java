package com.ayhanunlu.service;

import com.ayhanunlu.data.dto.RegisterDto;
import com.ayhanunlu.data.entity.JobSeekerEntity;
import com.ayhanunlu.data.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JobSeekerService {
    void registerNewJobSeeker(UserEntity registeredUserEntity, RegisterDto registerDto);
    JobSeekerEntity getJobSeekerEntityByUserId(Long userId);
    List<JobSeekerEntity> getAllJobSeekerEntities();
    List<JobSeekerEntity> getJobSeekerEntitiesFinishedMilitaryService();
    List<JobSeekerEntity> getJobSeekerEntitiesHaveExperience5PlusYears();
}
