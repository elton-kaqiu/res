package com.eltonkaqiu.resbackend.services;

import com.eltonkaqiu.resbackend.dtos.AuthenticationResponse;
import com.eltonkaqiu.resbackend.dtos.RegisterRequest;
import com.eltonkaqiu.resbackend.dtos.UserDto;
import com.eltonkaqiu.resbackend.models.User;

import java.util.List;

public interface UserService {
    AuthenticationResponse addUser(RegisterRequest req);

    UserDto updateUser(User newUser, Integer id);

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    void deleteUserById(Integer id);
}
