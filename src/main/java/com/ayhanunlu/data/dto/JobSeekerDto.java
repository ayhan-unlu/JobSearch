package com.ayhanunlu.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSeekerDto {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String fieldOfExperience;
    private int experienceYear;
    private boolean militaryServiceFinished;
}
