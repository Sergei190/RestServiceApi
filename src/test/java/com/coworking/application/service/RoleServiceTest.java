package com.coworking.application.service;

import com.coworking.domain.model.Role;
import com.coworking.domain.repo.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoleServiceTest {
    private RoleService roleService;
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        roleRepository = mock(RoleRepository.class);
        roleService = new RoleService(roleRepository);
    }

    @Test
    void testCreateRole() {
        Role role = new Role(0, "ADMIN");
        when(roleRepository.save(any(Role.class))).thenReturn(new Role(1, "ADMIN"));

        Role createdRole = roleService.createRole(role);

        assertNotNull(createdRole);
        assertEquals(1, createdRole.getId());
        assertEquals("ADMIN", createdRole.getRoleName());
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    void testGetRoleById() {
        Role role = new Role(1, "ADMIN");
        when(roleRepository.findById(1)).thenReturn(Optional.of(role));

        Optional<Role> retrievedRole = roleService.getRoleById(1);

        assertTrue(retrievedRole.isPresent());
        assertEquals(role, retrievedRole.get());
    }

    @Test
    void testDeleteRoleById() {
        when(roleRepository.deleteById(1)).thenReturn(true);

        boolean isDeleted = roleService.deleteRoleById(1);

        assertTrue(isDeleted);
        verify(roleRepository, times(1)).deleteById(1);
    }
}