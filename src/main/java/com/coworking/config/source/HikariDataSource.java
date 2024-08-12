package com.coworking.config.source;

import com.coworking.config.HikariConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * DataSource implementation for HikariCP connection pooling.
 */
public class HikariDataSource {
    private final HikariConfig config;
    private final BlockingQueue<Connection> connectionPool;

    /**
     * Constructor for HikariDataSource.
     *
     * @param config The configuration for the data source.
     */
    public HikariDataSource(HikariConfig config) {
        this.config = config;
        this.connectionPool = new ArrayBlockingQueue<>(config.getMaximumPoolSize());
        initializeConnectionPool();
    }

    /**
     * Initializes the connection pool with the specified number of connections.
     */
    private void initializeConnectionPool() {
        for (int i = 0; i < config.getMaximumPoolSize(); i++) {
            try {
                Connection connection = createConnection();
                connectionPool.offer(connection);
            } catch (SQLException e) {
                throw new RuntimeException("Failed to create initial connections", e);
            }
        }
    }

    /**
     * Creates a new database connection.
     *
     * @return A new Connection object.
     * @throws SQLException If a database access error occurs.
     */
    private Connection createConnection() throws SQLException {
        String jdbcUrl = config.getJdbcUrl();
        String username = config.getUsername();
        String password = config.getPassword();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC driver not found", e);
        }

        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    /**
     * Gets a connection from the connection pool.
     *
     * @return A Connection object.
     * @throws SQLException If a database access error occurs.
     */
    public Connection getConnection() throws SQLException {
        try {
            return connectionPool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SQLException("Interrupted while waiting for a connection", e);
        }
    }

    /**
     * Closes the data source and releases all resources.
     */
    public void close() {
        for (Connection connection : connectionPool) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}