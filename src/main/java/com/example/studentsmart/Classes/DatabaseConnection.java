package com.example.studentsmart.Classes;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.util.stream.*;

public class DatabaseConnection implements AutoCloseable {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/advoop";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "fox12345";
    public Connection connection;

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

    public void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }

    // Add this method inside the DatabaseConnection class
    public void openAssignmentURL(String subjectId, String weekColumn) {
        String query = "SELECT " + weekColumn + " FROM assignments WHERE subject_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, subjectId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String url = rs.getString(1);
                if (url != null && !url.isEmpty()) {
                    Desktop.getDesktop().browse(new URI(url));
                } else {
                    System.out.println("No URL found for this week.");
                }
            } else {
                System.out.println("No assignment found for subject: " + subjectId);
            }

        } catch (SQLException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public Map<String, String[]> getClassSchedule(String className) throws SQLException {
        Map<String, String[]> scheduleMap = new LinkedHashMap<>();
        String sql = "SELECT * FROM schedule WHERE className = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, className);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Add each day's schedule to the map
                scheduleMap.put("Sunday", new String[]{
                        rs.getString("sun1"), rs.getString("sun2"), rs.getString("sun3"),
                        "Break", rs.getString("sun4"), rs.getString("sun5"), rs.getString("sun6")
                });
                scheduleMap.put("Monday", new String[]{
                        rs.getString("mon1"), rs.getString("mon2"), rs.getString("mon3"),
                        "Break", rs.getString("mon4"), rs.getString("mon5"), rs.getString("mon6")
                });
                scheduleMap.put("Tuesday", new String[]{
                        rs.getString("tue1"), rs.getString("tue2"), rs.getString("tue3"),
                        "Break", rs.getString("tue4"), rs.getString("tue5"), rs.getString("tue6")
                });
                scheduleMap.put("Wednesday", new String[]{
                        rs.getString("wed1"), rs.getString("wed2"), rs.getString("wed3"),
                        "Break", rs.getString("wed4"), rs.getString("wed5"), rs.getString("wed6")
                });
                scheduleMap.put("Thursday", new String[]{
                        rs.getString("thu1"), rs.getString("thu2"), rs.getString("thu3"),
                        "Break", rs.getString("thu4"), rs.getString("thu5"), rs.getString("thu6")
                });
            }
        }

        return scheduleMap;
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
//            Map <String, Object> columnValues = new HashMap<>();
//            columnValues.put("studentName", "Yasmine Shehata");
//            columnValues.put("studentPhone","01044578645");
//            columnValues.put("studentEmail","yasmina@gmail.com");
//            columnValues.put("studentUserName","Yasmine");
//            columnValues.put("studentPassword","Yasmine");
//            columnValues.put("studentGender", "Female");
//            columnValues.put("studentAge", "18");
//            columnValues.put("className","Third Grade");
//            columnValues.put("classId","3B");
//            columnValues.put("studentReligion","Muslim");
//
//            db.insert("students", columnValues);

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