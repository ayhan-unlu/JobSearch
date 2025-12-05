package com.ayhanunlu.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name="users")
public class UserEntity extends BaseEntity implements Serializable {


}
