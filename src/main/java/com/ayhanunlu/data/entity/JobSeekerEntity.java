package com.ayhanunlu.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="job_seekers")
public class JobSeekerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id", nullable=false, unique=true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private UserEntity userEntity;

    @Column (name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="phone")
    private String phone;

    @Column(name="field_of_experience")
    private String fieldOfExperience;

    @Column(name="experience_year")
    private int experienceYear;

    @Column(name="is_military_service_finished")
    private boolean isMilitaryServiceFinished;
}
