package com.coworking.domain.repo;

import com.coworking.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface defining methods for user-related database operations.
 */
public interface UserRepository {

    /**
     * Saves a new user to the database.
     *
     * @param user The user to be saved.
     * @return The saved user with its generated ID.
     */
    User save(User user);

    /**
     * Finds a user by their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return An Optional containing the user if found, or empty if not.
     */
    Optional<User> findById(int id);

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all users.
     */
    List<User> findAll();

    /**
     * Updates an existing user in the database.
     *
     * @param user The user with updated information.
     * @return The updated user.
     */
    User update(User user);

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id The unique identifier of the user to be deleted.
     * @return True if the user was deleted, false otherwise.
     */
    boolean deleteById(int id);
}