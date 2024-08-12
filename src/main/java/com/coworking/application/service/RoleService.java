package com.coworking.application.service;

import com.coworking.domain.model.Role;
import com.coworking.domain.repo.RoleRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing roles in the system.
 */
public class RoleService {
    private final RoleRepository roleRepository;

    /**
     * Constructor for RoleService.
     *
     * @param roleRepository The repository used for role-related database operations.
     */
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Creates a new role in the system.
     *
     * @param role The role to be created.
     * @return The created role with its generated ID.
     */
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Retrieves a role by its unique identifier.
     *
     * @param id The unique identifier of the role.
     * @return An Optional containing the role if found, or empty if not.
     */
    public Optional<Role> getRoleById(int id) {
        return roleRepository.findById(id);
    }

    /**
     * Retrieves all roles in the system.
     *
     * @return A list of all roles.
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Updates an existing role in the system.
     *
     * @param role The role with updated information.
     * @return The updated role.
     */
    public Role updateRole(Role role) {
        return roleRepository.update(role);
    }

    /**
     * Deletes a role by its unique identifier.
     *
     * @param id The unique identifier of the role to be deleted.
     * @return True if the role was deleted, false otherwise.
     */
    public boolean deleteRoleById(int id) {
        return roleRepository.deleteById(id);
    }
}