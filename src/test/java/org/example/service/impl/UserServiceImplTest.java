package org.example.service.impl;

import org.example.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Test
    void testUserEntityGettersAndSetters() {
        // Given
        User user = new User();
        Long userId = 1L;
        String username = "testuser";
        String password = "password123";
        String role = "admin";
        String name = "Test User";
        String phone = "1234567890";
        String email = "test@example.com";

        // When
        user.setId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);

        // Then
        assertEquals(userId, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
        assertEquals(name, user.getName());
        assertEquals(phone, user.getPhone());
        assertEquals(email, user.getEmail());
    }

    @Test
    void testUserEntityNoArgsConstructor() {
        // When
        User user = new User();

        // Then
        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getRole());
        assertNull(user.getName());
        assertNull(user.getPhone());
        assertNull(user.getEmail());
    }
}