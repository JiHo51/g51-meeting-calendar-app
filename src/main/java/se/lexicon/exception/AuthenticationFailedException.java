package se.lexicon.exception;

public class AuthenticationFailedException extends Exception {
// --- Constructors ---
    // Constructor 'AuthenticationFailedException':
    public AuthenticationFailedException(String message) {
        super(message);
    } // Constructor 'AuthenticationFailedException' end --

    // Constructor 'AuthenticationFailedException':
    public AuthenticationFailedException(String message, Throwable cause) {
        super(message, cause);
    } // Constructor 'AuthenticationFailedException' end --

} // Class end --