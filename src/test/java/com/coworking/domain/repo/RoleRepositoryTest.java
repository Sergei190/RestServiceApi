package com.coworking.domain.repo;

import com.coworking.config.DatabaseConfig;
import com.coworking.core.jdbc.JdbcRoleRepository;
import com.coworking.domain.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RoleRepositoryTest {
    private RoleRepository roleRepository;
    private DatabaseConfig databaseConfig;

    @BeforeEach
    void setUp() {
        databaseConfig = new DatabaseConfig();
        roleRepository = new JdbcRoleRepository(databaseConfig);
        setupDatabase();
    }

    private void setupDatabase() {
        try (Connection connection = databaseConfig.getConnection()) {
            String dropTableSql = "DROP TABLE IF EXISTS roles";
            try (PreparedStatement dropStatement = connection.prepareStatement(dropTableSql)) {
                dropStatement.executeUpdate();
            }

            String createTableSql = "CREATE TABLE roles (id SERIAL PRIMARY KEY, role_name VARCHAR(255))";
            try (PreparedStatement createStatement = connection.prepareStatement(createTableSql)) {
                createStatement.executeUpdate();
            }

            String insertSql = "INSERT INTO roles (role_name) VALUES (?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                insertStatement.setString(1, "USER");
                insertStatement.executeUpdate();

                insertStatement.setString(1, "ADMIN");
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
            String dropTableSql = "DROP TABLE roles";
            try (PreparedStatement dropStatement = connection.prepareStatement(dropTableSql)) {
                dropStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error cleaning up database", e);
        }
    }

    @Test
    void testSaveRole() {
        Role role = new Role(0, "MODERATOR");
        Role savedRole = roleRepository.save(role);
        assertNotNull(savedRole.getId());
        assertEquals("MODERATOR", savedRole.getRoleName());
    }

    @Test
    void testFindById() {
        Role role = new Role(0, "GUEST");
        Role savedRole = roleRepository.save(role);
        Optional<Role> foundRole = roleRepository.findById(savedRole.getId());
        assertTrue(foundRole.isPresent());
        assertEquals(savedRole.getRoleName(), foundRole.get().getRoleName());
    }

    @Test
    void testFindAll() {
        List<Role> roles = roleRepository.findAll();
        assertEquals(2, roles.size());
    }
}