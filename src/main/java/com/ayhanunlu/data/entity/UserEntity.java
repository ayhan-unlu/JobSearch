package com.ayhanunlu.data.entity;

import com.ayhanunlu.enums.Role;
import com.ayhanunlu.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="users")
public class UserEntity extends BaseEntity implements Serializable {


    @Column (name="username")
    private String username;

    @Column (name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

    @Column(name="failed_login_attempts")
    private int failedLoginAttempts;

}
