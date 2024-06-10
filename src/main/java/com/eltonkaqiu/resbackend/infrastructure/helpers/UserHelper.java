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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserHelper {
    private static final Logger log = LoggerFactory.getLogger(UserHelper.class);
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
        log.debug("User before saving: {}", user);
        User savedUser = userRepository.save(user);
        log.debug("User saved with id {}", savedUser.getId());
        return savedUser;
    }


    public AuthenticationResponse authenticationResponse(User user) {
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public void updateUserDetails(User existingUser, User newUser, Integer id) {
        existingUser.setId(id);
        existingUser.setFirstName(newUser.getFirstName());
        existingUser.setLastName(newUser.getLastName());
        existingUser.setRole(newUser.getRole());
        existingUser.setEmail(newUser.getEmail());
        existingUser.setPassword(newUser.getPassword());
        existingUser.setCreatedAt(existingUser.getCreatedAt());
        existingUser.setUpdatedAt(LocalDateTime.now());
    }
}
