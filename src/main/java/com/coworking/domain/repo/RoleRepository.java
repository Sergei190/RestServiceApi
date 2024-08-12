package com.coworking.domain.repo;

import com.coworking.domain.model.Role;

import java.util.List;
import java.util.Optional;

/**
 * Interface defining methods for role-related database operations.
 */
public interface RoleRepository {

    /**
     * Saves a new role to the database.
     *
     * @param role The role to be saved.
     * @return The saved role with its generated ID.
     */
    Role save(Role role);

    /**
     * Finds a role by its unique identifier.
     *
     * @param id The unique identifier of the role.
     * @return An Optional containing the role if found, or empty if not.
     */
    Optional<Role> findById(int id);

    /**
     * Retrieves all roles from the database.
     *
     * @return A list of all roles.
     */
    List<Role> findAll();

    /**
     * Updates an existing role in the database.
     *
     * @param role The role with updated information.
     * @return The updated role.
     */
    Role update(Role role);

    /**
     * Deletes a role by its unique identifier.
     *
     * @param id The unique identifier of the role to be deleted.
     * @return True if the role was deleted, false otherwise.
     */
    boolean deleteById(int id);
}
