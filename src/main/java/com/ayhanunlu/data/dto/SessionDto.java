package com.ayhanunlu.data.dto;

import com.ayhanunlu.enums.Role;
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
}
