package com.ayhanunlu.data.dto;

import com.ayhanunlu.enums.Role;
import com.ayhanunlu.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {
    private Long id;
    private String username;
    private Role role;
    private Status status;
    private int failedLoginAttempts;
}
