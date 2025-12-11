package com.ayhanunlu.service;

import com.ayhanunlu.data.dto.RegisterDto;
import com.ayhanunlu.data.entity.JobSeekerEntity;
import com.ayhanunlu.data.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JobSeekerService {
    void registerNewJobSeeker(UserEntity registeredUserEntity, RegisterDto registerDto);
    JobSeekerEntity getJobSeekerEntityByUserId(Long userId);
    JobSeekerEntity getJobSeekerEntityByUserDetails(UserDetails userDetails);
    List<JobSeekerEntity> getAllJobSeekerEntities();
    List<JobSeekerEntity> getJobSeekerEntitiesFinishedMilitaryService();
    List<JobSeekerEntity> getJobSeekerEntitiesHaveExperience5PlusYears();

}
