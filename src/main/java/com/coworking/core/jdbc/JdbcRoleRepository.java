package com.coworking.core.jdbc;

import com.coworking.config.DatabaseConfig;
import com.coworking.domain.model.Role;
import com.coworking.domain.repo.RoleRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Concrete implementation of RoleRepository using JDBC to interact with the PostgreSQL database.
 */
public class JdbcRoleRepository implements RoleRepository {
    private final DatabaseConfig databaseConfig;

    /**
     * Constructor for JdbcRoleRepository.
     *
     * @param databaseConfig The database configuration.
     */
    public JdbcRoleRepository(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public Role save(Role role) {
        String sql = "INSERT INTO roles (role_name) VALUES (?) RETURNING id";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.getRoleName());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int generatedId = resultSet.getInt("id");
                    return new Role(generatedId, role.getRoleName());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving role", e);
        }
        throw new RuntimeException("Failed to save role");
    }

    @Override
    public Optional<Role> findById(int id) {
        String sql = "SELECT id, role_name FROM roles WHERE id = ?";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String roleName = resultSet.getString("role_name");
                    return Optional.of(new Role(id, roleName));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding role by ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Role> findAll() {
        String sql = "SELECT id, role_name FROM roles";
        List<Role> roles = new ArrayList<>();
        try (Connection connection = databaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String roleName = resultSet.getString("role_name");
                roles.add(new Role(id, roleName));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all roles", e);
        }
        return roles;
    }

    @Override
    public Role update(Role role) {
        String sql = "UPDATE roles SET role_name = ? WHERE id = ?";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.getRoleName());
            statement.setInt(2, role.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return role;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating role", e);
        }
        throw new RuntimeException("Failed to update role");
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM roles WHERE id = ?";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting role by ID", e);
        }
    }
}