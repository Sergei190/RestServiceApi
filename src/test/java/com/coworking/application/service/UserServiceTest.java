package com.coworking.application.service;

import com.coworking.domain.model.User;
import com.coworking.domain.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository, null);
    }

    @Test
    void testCreateUser() {
        User user = new User(0, "John Doe", "john.doe@example.com");
        when(userRepository.save(any(User.class))).thenReturn(new User(1, "John Doe", "john.doe@example.com"));

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(1, createdUser.getId());
        assertEquals("John Doe", createdUser.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserById() {
        User user = new User(1, "John Doe", "john.doe@example.com");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUserById(1);

        assertTrue(retrievedUser.isPresent());
        assertEquals(user, retrievedUser.get());
    }

    @Test
    void testDeleteUserById() {
        when(userRepository.deleteById(1)).thenReturn(true);

        boolean isDeleted = userService.deleteUserById(1);

        assertTrue(isDeleted);
        verify(userRepository, times(1)).deleteById(1);
    }
}