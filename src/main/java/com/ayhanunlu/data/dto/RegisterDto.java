package com.ayhanunlu.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private String fieldOfExperience;
    private int experienceYear;
    private boolean militaryServiceFinished;
}
