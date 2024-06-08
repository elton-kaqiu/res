package com.eltonkaqiu.resbackend.services.impls;

import com.eltonkaqiu.resbackend.dtos.AuthenticationResponse;
import com.eltonkaqiu.resbackend.dtos.RegisterRequest;
import com.eltonkaqiu.resbackend.dtos.UserDto;
import com.eltonkaqiu.resbackend.infrastructure.exceptions.EntityFoundException;
import com.eltonkaqiu.resbackend.infrastructure.exceptions.EntityNotFoundException;
import com.eltonkaqiu.resbackend.infrastructure.helpers.UserHelper;
import com.eltonkaqiu.resbackend.mappers.UserMapper;
import com.eltonkaqiu.resbackend.models.User;
import com.eltonkaqiu.resbackend.repositories.UserRepository;
import com.eltonkaqiu.resbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserHelper userHelper;

    @Override
    public AuthenticationResponse addUser(RegisterRequest req) {
        Optional<User> existingUser = userRepository.findByEmail(req.getEmail());
        if (existingUser.isPresent()) {
            throw new EntityFoundException(String.format("User with email: %s already exists", req.getEmail()));
        }
        User user = userHelper.createUserWithRole(req, "ADMIN");
        return userHelper.authenticationResponse(user);
    }

    @Override
    public UserDto updateUser(User newUser, Integer id) {
        User existingUser = userRepository.findByEmail(newUser.getEmail()).orElseThrow(
                () -> new EntityNotFoundException(String.format("User with email: %s not found", newUser.getEmail()))
        );
        userHelper.updateUserDetails(existingUser, newUser, id);
        var updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("User with id: %d not found", id))
        );
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .phoneNumber(user.getPhoneNumber())
                        .role(user.getRole().getName())
                        .email(user.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Integer id) {
        this.getUserById(id);
        userRepository.deleteById(id);
    }

}
