package se.lexicon.dao.impl;

import se.lexicon.dao.CalendarDAO;
import se.lexicon.exception.MySQLException;
import se.lexicon.model.Calendar;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CalendarDAOImpl implements CalendarDAO {
// --- Dependencies ---             // Injection of class - for access to class fields and methods
    private Connection connection;      // Injection of class 'Connection'


// --- Constructors ---
    // Constructor 'CalendarDAOImpl':
    public CalendarDAOImpl(Connection connection) {
        this.connection = connection;
    } // Constructor 'CalendarDAOImpl' end --


// --- Methods ---
    // Method 'createCalendar':
    @Override
    public Calendar createCalendar(String title, String username) {
        String insertQuery = "INSERT INTO calendars(username, title) VALUES(?, ?)";
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, title);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0) {
                String errorMessage = "Creating calendar failed, no rows affected.";
                throw new MySQLException(errorMessage);
            }
            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if(generatedKeys.next()) {
                    int calendarId = generatedKeys.getInt(1);
                    return new Calendar(calendarId, title, username);
                } else {
                    String errorMessage = "Creating calendar failed, no ID obtained.";
                    throw new MySQLException(errorMessage);
                }
            }
        } catch(SQLException e) {
            String errorMessage = "An error occurred while creating a calendar.";
            throw new MySQLException(errorMessage, e);
        }
    } // Method 'createCalendar' end --

    // Method 'findById':
    @Override
    public Optional<Calendar> findById(int id) {
        String selectQuery = "SELECT * FROM calendars WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    // Getting title and username from resultSet and set it to a new Calendar object:
                    String title = resultSet.getString("title");
                    String username = resultSet.getString("username");
                    return Optional.of(new Calendar(id, title, username));
                }
            }
        } catch(SQLException e) {
            String errorMessage = "Error occurred while finding MeetingCalendar by ID: " + id;
            throw new MySQLException(errorMessage, e);
        }
        return Optional.empty();
    } // Method 'findById' end --

    // Method 'findCalendarByUserName':
    @Override
    public Collection<Calendar> findCalendarByUserName(String username) {
        String selectQuery = "SELECT * FROM calendars WHERE username = ?";
        List<Calendar> calendars = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setString(1, username);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                // Iterate through the resultSet:
                while(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    calendars.add(new Calendar(id, title, username));
                }
            }
        } catch(SQLException e) {
            String errorMessage = "Error occurred while finding calendars by username: " + username;
            throw new MySQLException(errorMessage, e);
        }
        return calendars;
    } // Method 'findCalendarByUserName' end --

    // Method 'findByTitleAndUsername':
    @Override
    public Optional<Calendar> findByTitleAndUsername(String title, String username) {
        String selectQuery = "SELECT * FROM calendars WHERE title = ? AND username = ?";
        Calendar calendar = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, username);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    calendar = new Calendar(id, title, username);
                }
            }
        } catch(SQLException e) {
            String errorMessage = "Error occurred while finding MeetingCalendar by title: " + title;
            throw new MySQLException(errorMessage, e);
        }
        return Optional.ofNullable(calendar);
        // If calendar is non-null, it returns an Optional containing the calendar object.
        // If calendar is null, it returns an empty Optional.
    } // Method 'findByTitleAndUsername' end --

    // Method 'deleteCalendar':
    @Override
    public boolean deleteCalendar(int id) {
        String query = "DELETE FROM calendars WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch(SQLException e) {
            String errorMessage = "Error occurred while deleting MeetingCalendar by ID: " + id;
            throw new MySQLException(errorMessage, e);
        }
    } // Method 'deleteCalendar' end --

} // Class end --