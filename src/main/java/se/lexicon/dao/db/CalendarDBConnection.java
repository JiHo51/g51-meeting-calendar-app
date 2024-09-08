package se.lexicon.dao.db;

import se.lexicon.exception.DBConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CalendarDBConnection {
// --- Fields ---
    // --- Static Fields ---    // Static fields - data stored in memory allocated for class (no instances/objects)
    private static final String DB_NAME = "g51_meeting_calendar_db";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;    // Address to DB
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "ji83#sql45&ser%ver";

// --- Methods ---
    // Static Method 'getConnection':   // Static method - data stored in memory allocated for class
                                        // (no instances/objects)
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException e) {
            throw new DBConnectionException("Failed to connect to DB.");
        }
    } // Method 'getConnection' end --

} // Class end --