package se.lexicon.exception;

public class DBConnectionException extends RuntimeException {
// --- Constructors ---
    // Constructor 'DBConnectionException':
    public DBConnectionException(String message) {
        super(message);
    } // Constructor 'DBConnectionException' end --

    // Constructor 'DBConnectionException':
    public DBConnectionException(String message, Throwable cause) {
        super(message, cause);
    } // Constructor 'DBConnectionException' end --

} // Class end --