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
public class UserDto {
    private String firstName;
    private String lastName;
    private String role;
    private String phoneNumber;
    private String email;
}
