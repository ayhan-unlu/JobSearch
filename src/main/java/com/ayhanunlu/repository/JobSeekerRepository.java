package com.ayhanunlu.repository;

import com.ayhanunlu.data.entity.JobSeekerEntity;
import com.ayhanunlu.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeekerEntity, Long> {
    JobSeekerEntity findByUserEntity(UserEntity userEntity);

}
