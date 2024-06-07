package com.eltonkaqiu.resbackend.mappers;

import com.eltonkaqiu.resbackend.dtos.RoleDto;
import com.eltonkaqiu.resbackend.models.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final ModelMapper modelMapper;

    public Role toEntity(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }

    public RoleDto toDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }
}
