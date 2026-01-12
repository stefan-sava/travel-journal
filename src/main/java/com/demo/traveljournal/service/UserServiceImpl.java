package com.demo.traveljournal.service;

import com.demo.traveljournal.dto.*;
import com.demo.traveljournal.model.User;
import com.demo.traveljournal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {
        var user = userRepository.save(
                new User(
                        request.name(),
                        request.surname(),
                        request.email(),
                        request.password()
                )
        );
        return new UserResponse(user.getId(), user.getName(), user.getSurname(), user.getEmail());
    }

    public LoginResponse login(LoginRequest request) {
        var user = userRepository.findByEmail(request.email());
        if (user.isPresent() && user.get().getPassword().equals(request.password())) {
            return new LoginResponse(user.get().getId(), user.get().getId());
        }
        return new LoginResponse(0L, 0L);
    }

    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow();
        if(request.surname().isPresent()){
            user.setSurname(String.valueOf(request.surname()));
        }
        if(request.name().isPresent()){
            user.setName(String.valueOf(request.name()));
        }
        userRepository.save(user);
        return new UserResponse(user.getId(), user.getName(), user.getSurname(), user.getEmail());
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<UserResponse> getAllUsers() {
        var users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getSurname(), user.getEmail()))
                .toList();
    }

    public UserResponse getUserById(Long userId) {
        var user = userRepository.findById(userId);
        if (user.isPresent()) {
            return new UserResponse(user.get().getId(), user.get().getName(), user.get().getSurname(), user.get().getEmail());
        }
        return null;
    }





}
