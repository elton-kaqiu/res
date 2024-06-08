package com.eltonkaqiu.resbackend.services;

import com.eltonkaqiu.resbackend.dtos.AuthenticationRequest;
import com.eltonkaqiu.resbackend.dtos.AuthenticationResponse;
import com.eltonkaqiu.resbackend.dtos.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    AuthenticationResponse register(RegisterRequest registerRequest);
}
