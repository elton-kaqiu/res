package com.eltonkaqiu.resbackend.dtos;

import com.eltonkaqiu.resbackend.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Role role;
    private String email;
    private String password;
}
