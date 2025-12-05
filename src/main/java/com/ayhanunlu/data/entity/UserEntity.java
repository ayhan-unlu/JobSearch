package com.ayhanunlu.data.entity;

import com.ayhanunlu.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="users")
public class UserEntity extends BaseEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

}
