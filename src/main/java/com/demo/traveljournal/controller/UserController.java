package com.demo.traveljournal.controller;


import com.demo.traveljournal.dto.CreateUserRequest;
import com.demo.traveljournal.dto.LoginRequest;
import com.demo.traveljournal.dto.LoginResponse;
import com.demo.traveljournal.dto.UserResponse;
import com.demo.traveljournal.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel-journal")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String email, @RequestParam String password){
        LoginRequest loginRequest = new LoginRequest(email, password);
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
