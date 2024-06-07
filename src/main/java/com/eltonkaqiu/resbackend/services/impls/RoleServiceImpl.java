package com.eltonkaqiu.resbackend.services.impls;

import com.eltonkaqiu.resbackend.dtos.RoleDto;
import com.eltonkaqiu.resbackend.exceptions.EntityFoundException;
import com.eltonkaqiu.resbackend.mappers.RoleMapper;
import com.eltonkaqiu.resbackend.models.Role;
import com.eltonkaqiu.resbackend.repositories.RoleRepository;
import com.eltonkaqiu.resbackend.services.RoleService;
import jakarta.persistence.EntityNotFoundException;
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
    public RoleDto addRole(Role role) {
        Optional<Role> existingRoleOpt = roleRepository.findById(role.getId());
        if (existingRoleOpt.isPresent()) {
            throw new EntityFoundException("Role with id " + role.getId() + " already exists");
        }
        var newEntity = roleRepository.save(role);
        return roleMapper.toDto(newEntity);
    }

    @Override
    public RoleDto updateRole(Role newRole, Integer id) {
        Optional<Role> existingRoleOpt = roleRepository.findById(id);
        if (existingRoleOpt.isEmpty()) {
            throw new EntityNotFoundException("Role with id " + id + " not found");
        }
        Role existingRole = existingRoleOpt.get();
        updateRoleDetails(existingRole, newRole, id);
        var updatedEntity = roleRepository.save(existingRole);
        return roleMapper.toDto(updatedEntity);
    }

    @Override
    public RoleDto getRoleById(Integer id) {
        Optional<Role> existingRole = roleRepository.findById(id);
        if (existingRole.isEmpty()) {
            throw new EntityNotFoundException("Role with id " + id + " not found");
        }
        return roleMapper.toDto(existingRole.get());
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
    public void deleteRole(Integer id) {
        Optional<Role> existingRole = roleRepository.findById(id);
        if (existingRole.isEmpty()) {
            throw new EntityNotFoundException("Role with id " + id + " not found");
        }
        roleRepository.deleteById(id);
    }

    private void updateRoleDetails(Role existingRole, Role newRole, Integer id) {
        existingRole.setId(id);
        existingRole.setName(newRole.getName());
        existingRole.setCreatedAt(existingRole.getCreatedAt());
        existingRole.setUpdatedAt(LocalDateTime.now());
    }
}

