package com.eltonkaqiu.resbackend.services.impls;

import com.eltonkaqiu.resbackend.dtos.AuthenticationRequest;
import com.eltonkaqiu.resbackend.dtos.AuthenticationResponse;
import com.eltonkaqiu.resbackend.dtos.RegisterRequest;
import com.eltonkaqiu.resbackend.infrastructure.exceptions.EntityFoundException;
import com.eltonkaqiu.resbackend.infrastructure.exceptions.EntityNotFoundException;
import com.eltonkaqiu.resbackend.infrastructure.exceptions.NotAuthorizedException;
import com.eltonkaqiu.resbackend.infrastructure.helpers.UserHelper;
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
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
            );
        } catch (NotAuthorizedException ex) {
            throw new NotAuthorizedException("Authentication failed");
        }
        String jwtToken = jwtService.generateToken(user);
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
