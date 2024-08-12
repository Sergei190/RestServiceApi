package com.coworking.application.service;

import com.coworking.domain.model.Role;
import com.coworking.domain.model.User;
import com.coworking.domain.repo.RoleRepository;
import com.coworking.domain.repo.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for managing users in the system.
 */
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     * Constructor for UserService.
     *
     * @param userRepository The repository used for user-related database operations.
     * @param roleRepository The repository used for role-related database operations.
     */
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Creates a new user in the system.
     *
     * @param user The user to be created.
     * @return The created user with its generated ID.
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Assigns roles to a user.
     *
     * @param userId The ID of the user.
     * @param roleIds The IDs of the roles to be assigned.
     * @return The updated user with assigned roles.
     */
    public User assignRolesToUser(int userId, List<Integer> roleIds) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Set<Role> roles = new HashSet<>();
            for (int roleId : roleIds) {
                roleRepository.findById(roleId)
                        .ifPresent(roles::add);
            }
            user.setRoles(roles);
            return userRepository.update(user);
        }
        return null;
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return An Optional containing the user if found, or empty if not.
     */
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    /**
     * Retrieves all users in the system.
     *
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Updates an existing user in the system.
     *
     * @param user The user with updated information.
     * @return The updated user.
     */
    public User updateUser(User user) {
        return userRepository.update(user);
    }

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id The unique identifier of the user to be deleted.
     * @return True if the user was deleted, false otherwise.
     */
    public boolean deleteUserById(int id) {
        return userRepository.deleteById(id);
    }
}