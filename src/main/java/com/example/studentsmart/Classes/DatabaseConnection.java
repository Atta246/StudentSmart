package com.example.studentsmart.Classes;
import java.sql.*;
import java.util.*;
import java.util.stream.*;

public class DatabaseConnection implements AutoCloseable {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/advoop";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "fox12345";
    private Connection connection;

    public static class Subject {
        private String subjectId;
        private String subjectName;
        private int teacherId;
        private String teacherName;

        public Subject(String subjectId, String subjectName, int teacherId, String teacherName) {
            this.subjectId = subjectId;
            this.subjectName = subjectName;
            this.teacherId = teacherId;
            this.teacherName = teacherName;
        }

        // Getters
        public String getSubjectId() { return subjectId; }
        public String getSubjectName() { return subjectName; }
        public int getTeacherId() { return teacherId; }
        public String getTeacherName() { return teacherName; }

        @Override
        public String toString() {
            return String.format("%s %s %d %s", subjectId, subjectName, teacherId, teacherName);
        }
    }

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

    public Grades getStudentGrades(int studentId) throws SQLException {
        String sql = "SELECT * FROM grades WHERE studentId = ?";
        Grades grades = new Grades();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    grades = new Grades(
                            rs.getString("mathGrade1"),
                            rs.getString("mathGrade2"),
                            rs.getString("mathGrade3"),
                            rs.getString("mathGradeFinal"),
                            rs.getString("arabGrade1"),
                            rs.getString("arabGrade2"),
                            rs.getString("arabGrade3"),
                            rs.getString("arabGradeFinal"),
                            rs.getString("engGrade1"),
                            rs.getString("engGrade2"),
                            rs.getString("engGrade3"),
                            rs.getString("engGradeFinal"),
                            rs.getString("sciGrade1"),
                            rs.getString("sciGrade2"),
                            rs.getString("sciGrade3"),
                            rs.getString("sciGradeFinal"),
                            rs.getString("freGrade1"),
                            rs.getString("freGrade2"),
                            rs.getString("freGrade3"),
                            rs.getString("freGradeFinal"),
                            rs.getString("scoGrade1"),
                            rs.getString("scoGrade2"),
                            rs.getString("scoGrade3"),
                            rs.getString("scoGradeFinal")
                    );
                }
            }
        }

        return grades;
    }

    public List<Subject> getSubjects() throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT subjectId, subjectName, teacherId, teacherName FROM subjects";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String subjectId = rs.getString("subjectId");
                String subjectName = rs.getString("subjectName");
                int teacherId = rs.getInt("teacherId");
                String teacherName = rs.getString("teacherName");

                subjects.add(new Subject(subjectId, subjectName, teacherId, teacherName));
            }
        }

        return subjects;
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
            // Test getSubjects() method
            List<Subject> subjects = db.getSubjects();
            for (Subject subject : subjects) {
                System.out.println(subject);
            }

            // Example insert (commented out)
            // Map<String, Object> columnValues = new HashMap<>();
            // columnValues.put("subjectName", "French");
            // columnValues.put("subjectId", "Fre");
            // columnValues.put("teacherName", "Mrs/ Omnia");
            // columnValues.put("teacherId", 108);
            // db.insert("subjects", columnValues);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}