package com.ayhanunlu.service;

import com.ayhanunlu.data.entity.JobSeekerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JobSeekerService {
    JobSeekerEntity getJobSeekerEntityByUserId(Long userId);
    List<JobSeekerEntity> getAllJobSeekerEntities();
    List<JobSeekerEntity> getJobSeekerEntitiesFinishedMilitaryService();
    List<JobSeekerEntity> getJobSeekerEntitiesHaveExperience5PlusYears();
}
