package com.example.usermanagement.controller;

import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testAddUser() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"userId\": 1, \"title\": \"Title\", \"body\": \"Body\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.title", is("Title")))
                .andExpect(jsonPath("$.body", is("Body")));
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User(null, 1, "Title", "Body");
        user = userRepository.save(user);

        mockMvc.perform(get("/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(user.getUserId())))
                .andExpect(jsonPath("$.title", is(user.getTitle())))
                .andExpect(jsonPath("$.body", is(user.getBody())));
    }

    @Test
    void testDeleteUser() throws Exception {
        User user = new User(null, 1, "Title", "Body");
        user = userRepository.save(user);

        mockMvc.perform(delete("/users/{id}", user.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateUser() throws Exception {
        User user = new User(null, 1, "Old Title", "Old Body");
        user = userRepository.save(user);

        mockMvc.perform(put("/users/{id}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"userId\": 1, \"title\": \"New Title\", \"body\": \"New Body\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("New Title")))
                .andExpect(jsonPath("$.body", is("New Body")));
    }
}
