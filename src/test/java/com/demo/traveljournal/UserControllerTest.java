package com.demo.traveljournal;

import com.demo.traveljournal.controller.UserController;
import com.demo.traveljournal.dto.LoginResponse;
import com.demo.traveljournal.dto.UserResponse;
import com.demo.traveljournal.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserServiceImpl userService;

    @Test
    void getUserById() throws Exception {
        when(userService.getUserById(1L))
                .thenReturn(new UserResponse(
                        1L, "John", "Doe", "johndoe@email.com"));

        mockMvc.perform(get("/travel-journal/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void login_ok() throws Exception {
        when(userService.login(any()))
                .thenReturn(new LoginResponse(1L, 1L));

        mockMvc.perform(post("/travel-journal/login")
                        .param("email", "ana@test.com")
                        .param("password", "pass"))
                .andExpect(status().isOk());
    }
}
