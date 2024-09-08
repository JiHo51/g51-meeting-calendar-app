package se.lexicon.exception;

public class UserExpiredException extends Exception {
// --- Constructors ---
    // Constructor 'UserExpiredException':
    public UserExpiredException(String message) {
        super(message);
    } // Constructor 'UserExpiredException' end --

    // Constructor 'UserExpiredException':
    public UserExpiredException(String message, Throwable cause) {
        super(message, cause);
    } // Constructor 'UserExpiredException' end --

} // Class end --