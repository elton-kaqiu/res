package com.eltonkaqiu.resbackend.services;

import com.eltonkaqiu.resbackend.dtos.RoleDto;
import com.eltonkaqiu.resbackend.models.Role;

import java.util.List;

public interface RoleService {
    RoleDto addRole(Role role);

    RoleDto updateRole(Role role, Integer id);

    RoleDto getRoleById(Integer id);

    List<RoleDto> getAllRoles();

    void deleteRole(Integer id);
}
