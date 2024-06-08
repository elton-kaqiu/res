package com.eltonkaqiu.resbackend.controllers;

import com.eltonkaqiu.resbackend.dtos.AuthenticationRequest;
import com.eltonkaqiu.resbackend.dtos.AuthenticationResponse;
import com.eltonkaqiu.resbackend.dtos.RegisterRequest;
import com.eltonkaqiu.resbackend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register/")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authenticationService.register(req));
    }

    @PostMapping("/login/")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest req) {
        return ResponseEntity.ok(authenticationService.authenticate(req));
    }
}
