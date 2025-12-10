package com.ayhanunlu.config;

import com.ayhanunlu.service.VacancyService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class VacancyInitializer {

    @Autowired
    private VacancyService vacancyService;

    @PostConstruct
    public void initializeVacancies(){
        vacancyService.createDefaultVacancies();
    }
}
