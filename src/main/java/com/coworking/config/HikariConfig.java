package com.coworking.config;

/**
 * Configuration class for HikariCP connection pooling.
 */
public class HikariConfig {

    private String jdbcUrl;
    private String username;
    private String password;
    private int maximumPoolSize = 10; // Default maximum pool size
    private long connectionTimeout = 30000; // Default connection timeout in milliseconds
    private long idleTimeout = 600000; // Default idle timeout in milliseconds
    private long maxLifetime = 1800000; // Default max lifetime of a connection in milliseconds

    // Getters and setters for configuration properties

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public long getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public long getMaxLifetime() {
        return maxLifetime;
    }

    public void setMaxLifetime(long maxLifetime) {
        this.maxLifetime = maxLifetime;
    }
}
