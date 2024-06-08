package com.eltonkaqiu.resbackend.services.impls;

import com.eltonkaqiu.resbackend.dtos.RoleDto;
import com.eltonkaqiu.resbackend.infrastructure.exceptions.EntityFoundException;
import com.eltonkaqiu.resbackend.infrastructure.exceptions.EntityNotFoundException;
import com.eltonkaqiu.resbackend.mappers.RoleMapper;
import com.eltonkaqiu.resbackend.models.Role;
import com.eltonkaqiu.resbackend.repositories.RoleRepository;
import com.eltonkaqiu.resbackend.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleDto getRoleById(Integer id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Role with id: %d not found", id)));
        return roleMapper.toDto(role);
    }

    @Override
    public RoleDto addRole(Role role) {
        Optional<Role> existingRoleOpt = roleRepository.findById(role.getId());
        if (existingRoleOpt.isPresent()) {
            throw new EntityFoundException(String.format("Role with id: %d already exists", role.getId()));
        }
        var newRole = roleRepository.save(role);
        return roleMapper.toDto(newRole);
    }

    @Override
    public RoleDto updateRole(Role newRole, Integer id) {
        Role existingRole = roleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Role with id: %d not found", id)));
        updateRoleDetails(existingRole, newRole, id);
        var updatedEntity = roleRepository.save(existingRole);
        return roleMapper.toDto(updatedEntity);
    }


    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository
                .findAll()
                .stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRoleById(Integer id) {
        this.getRoleById(id);
        roleRepository.deleteById(id);
    }

    private void updateRoleDetails(Role existingRole, Role newRole, Integer id) {
        existingRole.setId(id);
        existingRole.setName(newRole.getName());
        existingRole.setCreatedAt(existingRole.getCreatedAt());
        existingRole.setUpdatedAt(LocalDateTime.now());
    }
}

