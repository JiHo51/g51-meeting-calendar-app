package se.lexicon.exception;

import se.lexicon.util.ConsoleColors;

public class CalendarExceptionHandler {
// --- Methods ---
    // Method 'handleException':    // Static method - data stored in memory allocated for class (no instances/objects)
    public static void handleException(Exception exception) {
        // AuthenticationFailedException:
        if(exception instanceof AuthenticationFailedException) {
            System.out.println(ConsoleColors.YELLOW + exception.getMessage() + ConsoleColors.RESET);
        // UserExpiredException:
        } else if(exception instanceof UserExpiredException) {
            System.out.println(ConsoleColors.RED + exception.getMessage() + ConsoleColors.RESET);
        // DBConnectionException:
        } else if(exception instanceof DBConnectionException) {
            System.out.println(exception.getMessage());
        // MySQLException:
        } else if(exception instanceof MySQLException) {
            System.out.println(exception.getMessage());
        // Other Exceptions:
        } else {
            System.out.println("An unexpected exception occurred.");
            exception.printStackTrace();
        }
    } // Method 'handleException' end --

} // Class end --