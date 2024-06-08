package com.eltonkaqiu.resbackend.services.impls;

import com.eltonkaqiu.resbackend.dtos.AuthenticationRequest;
import com.eltonkaqiu.resbackend.dtos.AuthenticationResponse;
import com.eltonkaqiu.resbackend.dtos.RegisterRequest;
import com.eltonkaqiu.resbackend.exceptions.EntityFoundException;
import com.eltonkaqiu.resbackend.exceptions.EntityNotFoundException;
import com.eltonkaqiu.resbackend.exceptions.NotAuthorizedException;
import com.eltonkaqiu.resbackend.helpers.UserHelper;
import com.eltonkaqiu.resbackend.models.Role;
import com.eltonkaqiu.resbackend.models.User;
import com.eltonkaqiu.resbackend.repositories.UserRepository;
import com.eltonkaqiu.resbackend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserHelper userHelper;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest req) {
        // Retrieve the user
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("User with email: %s not found", req.getEmail())));

        // Authenticate the user
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
            );
        } catch (NotAuthorizedException ex) {
            // Handle authentication failure
            throw new NotAuthorizedException("Authentication failed");
        }
        // Generate JWT token
        String jwtToken = jwtService.generateToken(user);

        // Return authentication response
        return new AuthenticationResponse(jwtToken);
    }


    @Override
    public AuthenticationResponse register(RegisterRequest req) {
        Optional<User> existingUser = userRepository.findByEmail(req.getEmail());
        if (existingUser.isPresent()) {
            throw new EntityFoundException(String.format("User with email: %s already exists", req.getEmail()));
        }
        User user = userHelper.createUserWithRole(req, "CUSTOMER");
        return userHelper.authenticationResponse(user);
    }
}
