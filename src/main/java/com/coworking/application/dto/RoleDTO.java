package com.coworking.application.dto;

/**
 * Data Transfer Object for transferring role data.
 */
public class RoleDTO {

    private int id;
    private String roleName;

    /**
     * Default constructor.
     */
    public RoleDTO() {}

    /**
     * Constructor for RoleDTO.
     *
     * @param id        Unique identifier for the role.
     * @param roleName  Name of the role.
     */
    public RoleDTO(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    /**
     * Gets the unique identifier of the role.
     *
     * @return The role ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the role.
     *
     * @param id The role ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the role.
     *
     * @return The role name.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the name of the role.
     *
     * @param roleName The role name to set.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
