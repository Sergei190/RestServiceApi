package com.coworking.domain.model;

import java.util.Objects;

/**
 * Represents a role entity in the system.
 */
public class Role {

    private final int id;
    private final String roleName;

    /**
     * Constructor for Role.
     *
     * @param id        Unique identifier for the role.
     * @param roleName  Name of the role.
     */
    public Role(int id, String roleName) {
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
     * Gets the name of the role.
     *
     * @return The role name.
     */
    public String getRoleName() {
        return roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
