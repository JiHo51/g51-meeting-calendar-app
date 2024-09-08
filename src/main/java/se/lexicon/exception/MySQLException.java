package se.lexicon.exception;

public class MySQLException extends RuntimeException {
// --- Constructors ---
    // Constructor 'MySQLException':
    public MySQLException(String message) {
        super(message);
    } // Constructor 'MySQLException' end --

    // Constructor 'MySQLException':
    public MySQLException(String message, Throwable cause) {
        super(message, cause);
    } // Constructor 'MySQLException' end --

} // Class end --