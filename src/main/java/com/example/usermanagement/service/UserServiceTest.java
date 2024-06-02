package com.example.usermanagement.service;

import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {
        User user = new User(null, 1, "Title", "Body");
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.addUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getUserId(), createdUser.getUserId());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserById() {
        User user = new User(1, 1, "Title", "Body");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserById(1);

        assertTrue(foundUser.isPresent());
        assertEquals(user.getId(), foundUser.get().getId());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    void testUpdateUser() {
        User existingUser = new User(1, 1, "Old Title", "Old Body");
        User updatedUser = new User(1, 1, "New Title", "New Body");

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        User result = userService.updateUser(1, updatedUser);

        assertNotNull(result);
        assertEquals(updatedUser.getTitle(), result.getTitle());
        assertEquals(updatedUser.getBody(), result.getBody());
    }
}
