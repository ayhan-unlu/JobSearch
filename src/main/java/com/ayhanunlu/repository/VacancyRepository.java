package com.ayhanunlu.repository;

import com.ayhanunlu.data.entity.VacancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<VacancyEntity,Long> {
    VacancyEntity findVacancyById(Long id);
    VacancyEntity findVacancyByTitle(String title);
/*    List<VacancyEntity> findAllVacancies();
    List<VacancyEntity> findVacanciesRequires5PlusExperienceYear();
    List<VacancyEntity> findVacanciesRequiresMilitaryServiceFinished();
    */
}
