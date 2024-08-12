package com.coworking.domain.repo;

import com.coworking.config.DatabaseConfig;
import com.coworking.core.jdbc.JdbcUserRepository;
import com.coworking.domain.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    private UserRepository userRepository;
    private DatabaseConfig databaseConfig;

    @BeforeEach
    void setUp() {
        databaseConfig = new DatabaseConfig();
        userRepository = new JdbcUserRepository(databaseConfig);
        setupDatabase();
    }

    private void setupDatabase() {
        try (Connection connection = databaseConfig.getConnection()) {
            String dropTableSql = "DROP TABLE IF EXISTS users";
            try (PreparedStatement dropStatement = connection.prepareStatement(dropTableSql)) {
                dropStatement.executeUpdate();
            }

            String createTableSql = "CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR(255), email VARCHAR(255))";
            try (PreparedStatement createStatement = connection.prepareStatement(createTableSql)) {
                createStatement.executeUpdate();
            }

            String insertSql = "INSERT INTO users (name, email) VALUES (?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                insertStatement.setString(1, "John Doe");
                insertStatement.setString(2, "john.doe@example.com");
                insertStatement.executeUpdate();

                insertStatement.setString(1, "Jane Smith");
                insertStatement.setString(2, "jane.smith@example.com");
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error setting up database", e);
        }
    }

    @AfterEach
    void tearDown() {
        cleanupDatabase();
    }

    private void cleanupDatabase() {
        try (Connection connection = databaseConfig.getConnection()) {
            String dropTableSql = "DROP TABLE users";
            try (PreparedStatement dropStatement = connection.prepareStatement(dropTableSql)) {
                dropStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error cleaning up database", e);
        }
    }

    @Test
    void testSaveUser() {
        User user = new User(0, "Sergei", "kudrasovcergej@gmail.com");
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
        assertEquals("Sergei", savedUser.getName());
    }

    @Test
    void testFindById() {
        User user = new User(0, "John Smith", "john.smith@example.com");
        User savedUser = userRepository.save(user);
        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertTrue(foundUser.isPresent());
        assertEquals(savedUser.getName(), foundUser.get().getName());
    }

    @Test
    void testFindAll() {
        List<User> users = userRepository.findAll();
        assertEquals(2, users.size());
    }
}