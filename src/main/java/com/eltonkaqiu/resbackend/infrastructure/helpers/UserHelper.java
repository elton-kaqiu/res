package com.eltonkaqiu.resbackend.infrastructure.helpers;

import com.eltonkaqiu.resbackend.dtos.AuthenticationResponse;
import com.eltonkaqiu.resbackend.dtos.RegisterRequest;
import com.eltonkaqiu.resbackend.infrastructure.exceptions.EntityNotFoundException;
import com.eltonkaqiu.resbackend.models.Role;
import com.eltonkaqiu.resbackend.models.User;
import com.eltonkaqiu.resbackend.repositories.RoleRepository;
import com.eltonkaqiu.resbackend.repositories.UserRepository;
import com.eltonkaqiu.resbackend.services.impls.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHelper {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User createUserWithRole(RegisterRequest req, String role) {
        Role defaultRole = roleRepository.findByName(role).orElseThrow(
                () -> new EntityNotFoundException("Role " + role + " not found")
        );
        User user = User.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .phoneNumber(req.getPhoneNumber())
                .role(defaultRole)
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();
        return userRepository.save(user);
    }

    public AuthenticationResponse authenticationResponse(User user) {
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
