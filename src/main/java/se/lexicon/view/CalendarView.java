package se.lexicon.view;

import se.lexicon.model.Calendar;
import se.lexicon.model.Meeting;
import se.lexicon.model.User;
import se.lexicon.util.ConsoleColors;

import java.util.List;

public interface CalendarView {                     // [Interface: Always 'abstract']
// --- Methods ---                                  [Methods: Always 'public' in Interface]
    // --- Abstract Methods ---     [Enforce SubClasses to implement these Methods (abstract)]

    // Method 'displayUser':            // Display user information
    void displayUser(User user);

    // Method 'displayCalendar':        // Display calendar information
    void displayCalendar(Calendar calendar);

    // Method 'displayMeetings':        // Display information all meetings
    void displayMeetings(List<Meeting> meetings);

    // Method 'promoteString':          // User input: string (this line, then start in next line)
    String promoteString();

    // Method 'promoteUserForm':        // User input: User Form = username & password
    User promoteUserForm();

    // Method 'promoteMeetingForm':     // User input: Meeting Form = title, startDateTime, endDateTime, description
    Meeting promoteMeetingForm();

    // Method 'promoteCalendarForm':    // User input: Calendar Form = title
    String promoteCalendarForm();


    // --- Default Methods ---      [Default (optional) implementation for all classes implementing this interface]

    // Method 'displayMenu':            // Display Menu
    default void displayMenu() {
        System.out.println("Calendar Options:");
        System.out.println("0.\tRegister");
        System.out.println("1.\tLogin");
        System.out.println("2.\tAdd Calendar");
        System.out.println("3.\tAdd Meeting");
        System.out.println("4.\tDelete Calendar");
        System.out.println("5.\tDisplay Meeting");
        System.out.println("6.\tLogout");
        System.out.println("7.\tExit");
        System.out.println("Enter your choice:");
    } // Method 'displayMenu' end --

    // Method 'displayMessage':                                                 // General messages to user
    default void displayMessage(String message) {
        System.out.println(ConsoleColors.BLUE + message + ConsoleColors.RESET);
    } // Method 'displayMessage' end --

    // Method 'displaySuccessMessage':                                          // Operation success messages to user
    default void displaySuccessMessage(String message) {
        System.out.println(ConsoleColors.GREEN + message + ConsoleColors.RESET);
    } // Method 'displaySuccessMessage' end --

    // Method 'displayWarningMessage':                                          // Operation warning messages to user
    default void displayWarningMessage(String message) {
        System.out.println(ConsoleColors.YELLOW + message + ConsoleColors.RESET);
    } // Method 'displayWarningMessage' end --

    // Method 'displayErrorMessage':                                            // Operation error messages to user
    default void displayErrorMessage(String message) {
        System.out.println(ConsoleColors.RED + message + ConsoleColors.RESET);
    } // Method 'displayErrorMessage' end --

} // Interface end --