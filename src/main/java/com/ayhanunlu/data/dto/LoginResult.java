package com.ayhanunlu.data.dto;

import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.LoginResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoginResult {

    private LoginResponse loginResponse;
    private UserEntity userEntity;

}
