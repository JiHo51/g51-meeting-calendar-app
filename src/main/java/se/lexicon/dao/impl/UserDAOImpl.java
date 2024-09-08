package se.lexicon.dao.impl;

import se.lexicon.dao.UserDAO;
import se.lexicon.exception.AuthenticationFailedException;
import se.lexicon.exception.MySQLException;
import se.lexicon.exception.UserExpiredException;
import se.lexicon.model.User;

import java.sql.*;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
// --- Dependencies ---             // Injection of class - for access to class fields and methods
    private Connection connection;      // Injection of class 'Connection'


// --- Constructors ---
    // Constructor 'UserDAOImpl':
    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    } // Constructor 'UserDAOImpl' end --


// --- Methods ---
    // Method 'createUser':
    @Override
    public User createUser(String username) {
        String query = "INSERT INTO users(username, _password) VALUES(?, ?)";
        try(
            //Connection connection = CalendarDBConnection.getConnection(); - Replaced by Dependencies injection above.
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ) {
            User user = new User(username);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getHashedPassword());
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows == 0) {
                throw new MySQLException("Creating user failed, no rows affected.");
            }
            return user;
        } catch(SQLException e) {
            throw new MySQLException("Error occurred while creating user: " + username, e);
        }
    } // Method 'createUser' end --

    // Method 'findByUsername':
    @Override
    public Optional<User> findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String foundUsername = resultSet.getString("username");
                String foundPassword = resultSet.getString("_password");
                boolean foundExpired = resultSet.getBoolean("expired");
                User user = new User(foundUsername, foundPassword, foundExpired);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding user by username: " + username, e);
        }
    } // Method 'findByUsername' end --

    // Method 'authenticate':
    @Override
    public boolean authenticate(User user) throws AuthenticationFailedException, UserExpiredException {
        // Step 1: Define a select query:
        String query = "SELECT * FROM users WHERE username = ? AND _password = ?";

        // Step 2: Create a prepared statement:
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Step 3: Set parameters to the statement:
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            // Step 4: Execute the query:
            ResultSet resultSet = preparedStatement.executeQuery();

            // Step 5: Check the resultSet:
            // Step 5.1: If the resultSet exists:
            if(resultSet.next()) {
                // Step 5.2: Check if the user is expired - > throw exception:
                boolean isExpired = resultSet.getBoolean("expired");
                if(isExpired) {
                    throw new UserExpiredException("User is expired. Username: " + user.getUsername());
                }
            } else { // Step 5.3: Else if the resultSet was null (no "next") -> throw other exception:
                throw new AuthenticationFailedException("Authentication failed. Invalid credentials.");
            }
            // Step 6: Return true: - If authentication succeeded:
            return true;
        } catch(SQLException e) {
            throw new MySQLException("Error occurred while authenticating user by username: " + user.getUsername());
        }
    } // Method 'authenticate' end --

} // Class end --