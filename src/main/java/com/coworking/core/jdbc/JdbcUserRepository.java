package com.coworking.core.jdbc;

import com.coworking.config.DatabaseConfig;
import com.coworking.domain.model.User;
import com.coworking.domain.repo.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Concrete implementation of UserRepository using JDBC to interact with the PostgreSQL database.
 */
public class JdbcUserRepository implements UserRepository {
    private final DatabaseConfig databaseConfig;

    /**
     * Constructor for JdbcUserRepository.
     *
     * @param databaseConfig The database configuration.
     */
    public JdbcUserRepository(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?) RETURNING id";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int generatedId = resultSet.getInt("id");
                    return new User(generatedId, user.getName(), user.getEmail());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user", e);
        }
        throw new RuntimeException("Failed to save user");
    }

    @Override
    public Optional<User> findById(int id) {
        String sql = "SELECT id, name, email FROM users WHERE id = ?";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    return Optional.of(new User(id, name, email));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT id, name, email FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = databaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                users.add(new User(id, name, email));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all users", e);
        }
        return users;
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user", e);
        }
        throw new RuntimeException("Failed to update user");
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user by ID", e);
        }
    }
}