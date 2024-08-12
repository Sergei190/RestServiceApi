package com.coworking.domain.model;

import java.util.Objects;

/**
 * Represents the many-to-many relationship between users and roles.
 */
public class UserRole {

    private final User user;
    private final Role role;

    /**
     * Constructor for UserRole.
     *
     * @param user  User associated with the role.
     * @param role  Role associated with the user.
     */
    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    /**
     * Gets the user associated with the role.
     *
     * @return The user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the user associated with the role.
     *
     * @return The user.
     */
    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(user, userRole.user) &&
                Objects.equals(role, userRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, role);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "user=" + user +
                ", role=" + role +
                '}';
    }
}
