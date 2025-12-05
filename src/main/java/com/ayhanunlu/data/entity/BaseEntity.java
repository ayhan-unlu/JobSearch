package com.ayhanunlu.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name="username")
    private String username;

    @Column (name="password")
    private String password;




}
