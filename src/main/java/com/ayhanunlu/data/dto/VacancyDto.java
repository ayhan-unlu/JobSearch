package com.ayhanunlu.data.dto;

import com.ayhanunlu.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyDto {

    private Long id;
    private String title;
    private String fieldOfExperience;
    private int experienceYear;
    private boolean militaryServiceFinished;
}
