package com.coworking.application.dto;

import java.util.Set;

/**
 * Data Transfer Object for transferring user data.
 */
public class UserDTO {

    private int id;
    private String name;
    private String email;
    private Set<Integer> roleIds;

    /**
     * Default constructor.
     */
    public UserDTO() {}

    /**
     * Constructor for UserDTO.
     *
     * @param id      Unique identifier for the user.
     * @param name    Name of the user.
     * @param email   Email of the user.
     * @param roleIds Set of role IDs associated with the user.
     */
    public UserDTO(int id, String name, String email, Set<Integer> roleIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roleIds = roleIds;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return The user ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id The user ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the user.
     *
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The user's name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the user.
     *
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The user's email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the set of role IDs associated with the user.
     *
     * @return The set of role IDs.
     */
    public Set<Integer> getRoleIds() {
        return roleIds;
    }

    /**
     * Sets the set of role IDs associated with the user.
     *
     * @param roleIds The set of role IDs to set.
     */
    public void setRoleIds(Set<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}
