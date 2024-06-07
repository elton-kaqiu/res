package com.eltonkaqiu.resbackend.controllers;

import com.eltonkaqiu.resbackend.dtos.RoleDto;
import com.eltonkaqiu.resbackend.models.Role;
import com.eltonkaqiu.resbackend.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        try {
            return ResponseEntity.ok(roleService.getAllRoles());
        } catch (Exception _) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(roleService.getRoleById(id));
        } catch (Exception _) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<RoleDto> createRole(@RequestBody Role role) {
        try {
            return ResponseEntity.ok(roleService.addRole(role));
        } catch (Exception _) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateRole(@RequestBody Role role, @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(roleService.updateRole(role, id));
        } catch (Exception _) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.noContent().build();
        } catch (Exception _) {
            return ResponseEntity.badRequest().build();
        }
    }


}
