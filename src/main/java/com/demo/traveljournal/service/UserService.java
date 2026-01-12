package com.demo.traveljournal.service;

import com.demo.traveljournal.dto.*;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    LoginResponse login(LoginRequest request);


    void deleteUser(Long userId);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);
}
