package com.demo.traveljournal.dto;

public record CreateUserRequest(String name, String surname, String email, String password) {
}
