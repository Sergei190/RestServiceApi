package com.coworking.domain.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a user entity in the system.
 */
public class User {

    private final int id;
    private final String name;
    private final String email;
    private Set<Role> roles;

    /**
     * Constructor for User.
     *
     * @param id    Unique identifier for the user.
     * @param name  Name of the user.
     * @param email Email of the user.
     */
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = new HashSet<>();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
     * Gets the name of the user.
     *
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email of the user.
     *
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
