package com.demo.traveljournal.dto;

import java.util.Optional;

public record UpdateUserRequest(Optional<String> name, Optional<String> surname) {
}
