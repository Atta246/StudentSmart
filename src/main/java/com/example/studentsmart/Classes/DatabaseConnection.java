package com.example.studentsmart.Classes;
import java.sql.*;
import java.util.*;
import java.util.stream.*;
public class DatabaseConnection implements AutoCloseable {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/advoop";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "yasmine123";
    private Connection connection;

    public DatabaseConnection() throws SQLException {
        connect();
    }

    private void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }

    public boolean insert(String tableName, Map<String, Object> columnValues) {
        if (tableName == null || columnValues == null || columnValues.isEmpty()) {
            return false;
        }

        // Build SQL query
        String columns = String.join(", ", columnValues.keySet());
        String placeholders = columnValues.keySet().stream()
                .map(k -> "?")
                .collect(Collectors.joining(", "));

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)",
                tableName, columns, placeholders);

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set parameter values
            int i = 1;
            for (Object value : columnValues.values()) {
                stmt.setObject(i++, value);
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Insert failed: " + e.getMessage());
            return false;
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public int executeUpdate(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sql);
        }
    }

    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commitTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public void rollbackTransaction() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try (DatabaseConnection db = new DatabaseConnection()) {
            // Example query
            ResultSet rs = db.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("studentId"));
                System.out.println("User: " + rs.getString("studentName"));
                System.out.println("Date: " + rs.getString("studentEmail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}