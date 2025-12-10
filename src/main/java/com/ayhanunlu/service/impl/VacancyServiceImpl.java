package com.ayhanunlu.service.impl;

import com.ayhanunlu.data.entity.VacancyEntity;
import com.ayhanunlu.repository.VacancyRepository;
import com.ayhanunlu.service.VacancyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class VacancyServiceImpl implements VacancyService {
    @Autowired
    private VacancyRepository vacancyRepository;

    @Override
    public void createDefaultVacancies() {

        for (int i = 1; i <= 10; i++) {
            String searchedVacancyTitle = "Vacancy-" + i;
            if (vacancyRepository.findVacancyByTitle(searchedVacancyTitle) == null) {
                VacancyEntity newVacancyEntity = new VacancyEntity();
                String title = "Vacancy-" + i;
                newVacancyEntity.setTitle(title);
                newVacancyEntity.setFieldOfExperience(String.valueOf(i));
                newVacancyEntity.setExperienceYear(i);
                if (i % 2 == 0) {
                    newVacancyEntity.setMilitaryServiceFinished(true);
                } else {
                    newVacancyEntity.setMilitaryServiceFinished(false);
                }
                vacancyRepository.save(newVacancyEntity);
                log.info("Vacancy: {} created successfully", title);
            } else {
                log.info("Vacancy: {} is not created again.Cause it already exists in the DB", searchedVacancyTitle);
            }
        }
    }

    public List<VacancyEntity> findAllVacancies(){
        log.info("All Vacancies are Listed");
        return vacancyRepository.findAll();
    }

    public List<VacancyEntity> findVacanciesRequires5PlusExperienceYear(){
        List<VacancyEntity> filteredVacancyEntityList = new ArrayList<>();
        for(VacancyEntity vacancyEntity : vacancyRepository.findAll()){
            if(vacancyEntity.getExperienceYear()>=5){
                filteredVacancyEntityList.add(vacancyEntity);
            }
        }
        log.info("Vacancies which requires 5+ Years of Experience are filtered");
        return filteredVacancyEntityList;
    }

    public List<VacancyEntity> findVacanciesRequiresMilitaryServiceFinished(){
        List<VacancyEntity> filteredVacancyEntityList = new ArrayList<>();
        for(VacancyEntity vacancyEntity :vacancyRepository.findAll()){
            if(vacancyEntity.isMilitaryServiceFinished()){
                filteredVacancyEntityList.add(vacancyEntity);
            }
        }
        log.info("Vacancies which requires Military Service finished are filtered");
        return filteredVacancyEntityList;
    }

}
