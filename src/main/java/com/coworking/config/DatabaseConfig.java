package com.coworking.config;

import com.coworking.config.source.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Configuration class for setting up the database connection and managing connection pooling.
 */
public class DatabaseConfig {

    private final HikariDataSource dataSource;

    /**
     * Constructor for DatabaseConfig.
     */
    public DatabaseConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("Ser19052001");
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);

        this.dataSource = new HikariDataSource(config);
    }

    /**
     * Gets a connection from the connection pool.
     *
     * @return A Connection object.
     * @throws SQLException If a database access error occurs.
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Closes the data source and releases all resources.
     */
    public void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
