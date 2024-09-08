package se.lexicon.model;

import java.security.SecureRandom;
import java.util.Random;

public class User {
// --- Fields ---
    // --- Instance Fields ---
    private final String username;
    private String password;
    private boolean expired;


// --- Constructors ---
    // Constructor 'User':           - Used for registering a new user
    public User(String username) {
        this.username = username;
        newPassword();              // Generate a random password and send to instance password at instantiation
    } // Constructor 'User' end --

    // Constructor 'User':           - Used for authentication
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    } // Constructor 'User' end --

    // Constructor 'User':           - Used for fetching data from DB
    public User(String username, String password, boolean expired) {
        this(username, password);
        this.expired = expired;
    } // Constructor 'User' end --


// --- Setters & Getters ---
    // --- Getters ---              // Enables outside reading of the private variables of this class

    // Getter 'getUsername':
    public String getUsername() {
        return username;
    } // Getter Method 'getUsername' end --

    // Getter 'getPassword':
    public String getPassword() {
        return password;
    } // Getter Method 'getPassword' end --

    // Getter 'isExpired':
    public boolean isExpired() {
        return expired;
    } // Getter Method 'isExpired' end --


// --- Methods ---
    // Method 'userInfo':
    public String userInfo() {
    return "Username: " + username + ", Password: " + password;
    } // Method 'userInfo' end --

    // Method 'generateRandomPassword':             // Generate a random password
    private String generateRandomPassword() {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int passwordLength = 10;
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new SecureRandom();         // Secure Random Number Generator (RNG)

        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());   // Length of 'allowedCharacters' (1)
            char randomChar = allowedCharacters.charAt(randomIndex);        // Select character [0..(length-1)] (-> B)
            stringBuilder.append(randomChar);                               // Build 10 sign password 1 char/iteration
        }
        return stringBuilder.toString();
    } // Method 'generateRandomPassword' end --

    // Method 'newPassword':                        // Generate a random password and send to instance password
    public void newPassword() {
        this.password = generateRandomPassword();
    } // Method 'newPassword' end --

} // Class end --