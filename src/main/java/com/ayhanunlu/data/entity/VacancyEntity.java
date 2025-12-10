package com.ayhanunlu.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="vacancies")
public class VacancyEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="field_of_experience")
    private String fieldOfExperience;

    @Column(name="experience_year")
    private int experienceYear;

    @Column (name="military_service_finished")
    private boolean militaryServiceFinished;

}
