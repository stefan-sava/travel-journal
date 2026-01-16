package com.demo.traveljournal;

import com.demo.traveljournal.dto.CreateUserRequest;
import com.demo.traveljournal.dto.LoginRequest;
import com.demo.traveljournal.dto.LoginResponse;
import com.demo.traveljournal.dto.UserResponse;
import com.demo.traveljournal.model.User;
import com.demo.traveljournal.repository.UserRepository;
import com.demo.traveljournal.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_ShouldReturnCreatedUser() {
        CreateUserRequest req =
                new CreateUserRequest("testuser", "testUser", "a", "a");

        User saved = new User("testuser", "testUser", "a", "a");

        saved.setId(1L);

        when(userRepository.save(any(User.class))).thenReturn(saved);

        UserResponse res = userService.createUser(req);

        assertEquals(1L, res.id());
        assertEquals("testuser", res.name());
        assertEquals("testUser", res.surname());
        assertEquals("a", res.email());
    }

    @Test
    void login_OK_ShouldReturnUserResponse() {
        User user = new User("bb", "bb", "bb@test.com", "bb");
        user.setId(2L);

        when(userRepository.findByEmail("bb@test.com"))
                .thenReturn(Optional.of(user));

        LoginResponse res =
                userService.login(new LoginRequest("bb@test.com", "bb"));

        assertEquals(2L, res.id());
    }

    @Test
    void getUserById_NotFound_ShouldThrowException() {
        Long userId = 99L;

        when(userRepository.findById(userId))
                .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            userService.getUserById(userId);
        });
    }
}
