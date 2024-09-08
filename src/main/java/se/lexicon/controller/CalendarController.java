package se.lexicon.controller;

import se.lexicon.dao.CalendarDAO;
import se.lexicon.dao.MeetingDAO;
import se.lexicon.dao.UserDAO;
import se.lexicon.exception.CalendarExceptionHandler;
import se.lexicon.model.Calendar;
import se.lexicon.model.Meeting;
import se.lexicon.model.User;
import se.lexicon.view.CalendarView;

import java.util.*;

public class CalendarController {
// --- Dependencies ---             // Injection of class - for access to class fields and methods (instance storage)
    private CalendarView view;          // Injection of class 'CalendarView'
    private UserDAO userDAO;            // Injection of class 'UserDAO'
    private CalendarDAO calendarDAO;    // Injection of class 'CalendarDAO'
    private MeetingDAO meetingDAO;      // Injection of class 'MeetingDAO'


// --- Fields ---
    // --- Static Fields ---        // Fields belonging to this class - accessible from all local methods


    // --- Instance Fields ---      // Fields belonging to instances of this class - accessible from all local methods
    private boolean isLoggedIn;
    private String username;


// --- Constructors ---
    // Constructor 'CalendarController':
    public CalendarController(CalendarView view, UserDAO userDAO, CalendarDAO calendarDAO, MeetingDAO meetingDAO) {
        this.view = view;
        this.userDAO = userDAO;
        this.calendarDAO = calendarDAO;
        this.meetingDAO = meetingDAO;
    } // Constructor 'CalendarController' end --


// --- Methods ---
    // Method 'run':                            // "Run application": Display Menu - execute methods by user choice
    public void run() {
        while(true) {                           // Infinite loop
            view.displayMenu();
            int choice = getUserChoice();       // Return value from method for user choice

            switch(choice) {                    // Evaluate value from method for user choice - call methods
                case 0:
                    register();                 // Call method for registering new user
                    break;
                case 1:
                    login();                    // Call method for user login
                    System.out.println("Login method has been executed.");              // TEST
                    break;
                case 2:
                    createCalendar();           // Call method for creating a calendar
                    break;
                case 3:
                    createMeeting();            // Call method for creating a meeting
                    break;
                case 4:
                    deleteCalendar();           // Call method for deleting a calendar
                    break;
                case 5:
                    displayCalendar();          // Call method for displaying a calendar
                    break;
                case 6:
                    logout();                   // Call method for user logout
                    break;
                case 7:
                    exit();                     // Call method for terminating the application
                    break;
                default:                        // Triggered when user input not matching any case
                    view.displayWarningMessage("Invalid choice. Please select a valid option.");
            } // Switch statement end --
        } // While statement end --
    } // Method 'run' end --

    // Method 'getUserChoice':              // Method for user choice
    private int getUserChoice() {
        String operationType = view.promoteString();
        int choice = -1;
        try {
            choice = Integer.parseInt((operationType));
        } catch(NumberFormatException e) {
            view.displayErrorMessage("Invalid input, please enter a number.");
        }
        return choice;
    } // Method 'getUserChoice' end --

    // Method 'register':                   // Method for registering new user
    private void register() {
        view.displayMessage("Enter your username:");
        String username = view.promoteString();
        User registeredUser = userDAO.createUser(username);
        view.displayUser(registeredUser);
    } // Method 'register' end --

    // Method 'login':                      // Method for user login
    private void login() {
        User user = view.promoteUserForm();
        try {
            isLoggedIn = userDAO.authenticate(user);
            username = user.getUsername();
            view.displaySuccessMessage("Login successful. Welcome " + username + "!");
        } catch (Exception e) {
            CalendarExceptionHandler.handleException(e);
        }
    } // Method 'login' end --

    // Method 'createCalendar':             // Method for creating a calendar
    private void createCalendar() {
        if(!isLoggedIn) {
            view.displayWarningMessage("You need to login first.");
            return;                         // Stops method executing
        }
        String calendarTitle = view.promoteCalendarForm();
        Calendar createdCalendar = calendarDAO.createCalendar(calendarTitle, username);
        view.displaySuccessMessage("Calendar created successfully.");
        view.displayCalendar(createdCalendar);
    } // Method 'createCalendar' end --

    // Method 'createMeeting':              // Method for creating a meeting
    private void createMeeting() {
        if(!isLoggedIn) {
            view.displayWarningMessage("You need to login first.");
            return;                         // Stops method executing
        }
        System.out.println("Available calendars:");
        Collection<Calendar> calendars = calendarDAO.findCalendarByUserName(username);
        for(Calendar calendar : calendars) {
            System.out.println("Calendar title: " + calendar.getTitle());
        }
        String calendarTitle = view.promoteCalendarForm();
        Optional<Calendar> meetingCalendarOptional = calendarDAO.findByTitleAndUsername(calendarTitle, username);
        if(!meetingCalendarOptional.isPresent()) {
            view.displayErrorMessage("Meeting calendar doesn't exist.");
            return;                         // Stops method executing
        }
        Meeting newMeeting = view.promoteMeetingForm();
        newMeeting.setCalendar(meetingCalendarOptional.get());

        // Validate the meeting times before sending it to the DAO (Data Access Object layer)
        try {
            newMeeting.timeValidation();
        } catch(IllegalArgumentException e) {
            view.displayErrorMessage(e.getMessage());
            return;                         // Stops method executing
        }
        Meeting createdMeeting = meetingDAO.createMeeting(newMeeting);
        view.displaySuccessMessage("Meeting created successfully.");
        view.displayMeetings(Collections.singletonList(createdMeeting));
    } // Method 'createMeeting' end --

    // Method 'deleteCalendar':             // Method for deleting a calendar
    private void deleteCalendar() {
        if(!isLoggedIn) {
            view.displayWarningMessage("You need to log in first.");
            return;                         // Stops method executing
        }
        System.out.println("Choose title from available calendars: ");
        Collection<Calendar> calendars = calendarDAO.findCalendarByUserName(username);
        for(Calendar calendar : calendars) {
            System.out.println("Title: " + calendar.getTitle());
        }
        String calendarTitle = view.promoteString();
        Optional<Calendar> meetingCalendarOptional = calendarDAO.findByTitleAndUsername(calendarTitle, username);
        if(!meetingCalendarOptional.isPresent()) {
            view.displayErrorMessage("Meeting calendar doesn't exist.");
            return;                         // Stops method executing
        }
        Calendar meetingCalendar = meetingCalendarOptional.get();

        // Delete associated meetings first:
        for(Meeting meeting : meetingDAO.findAllMeetingsByCalendarId(meetingCalendar.getId())) {
            meetingDAO.deleteMeeting(meeting.getId());
        }
        boolean isDeleted = calendarDAO.deleteCalendar(meetingCalendar.getId());
        if(isDeleted) {
            view.displaySuccessMessage("Calendar deleted successfully.");
        } else {
            view.displayWarningMessage("Failed to delete the calendar.");
        }
    } // Method 'deleteCalendar' end --

    // Method 'displayCalendar':            // Method for displaying a calendar
    private void displayCalendar() {
        if(!isLoggedIn) {
            view.displayWarningMessage("You need to log in first.");
            return;                         // Stops method executing
        }
        System.out.println("Choose a calendar to display:");
        Collection<Calendar> calendars = calendarDAO.findCalendarByUserName(username);
        for(Calendar calendar : calendars) {
            System.out.println("Title: " + calendar.getTitle());
        }
        String calendarTitle = view.promoteString();
        Optional<Calendar> meetingCalendarOptional = calendarDAO.findByTitleAndUsername(calendarTitle, username);
        if(!meetingCalendarOptional.isPresent()) {
            view.displayErrorMessage("Meeting calendar doesn't exist.");
            return;                         // Stops method executing
        }
        Calendar selectedCalendar = meetingCalendarOptional.get();
        view.displayCalendar(selectedCalendar);

        List<Meeting> meetings = meetingDAO.findAllMeetingsByCalendarId(selectedCalendar.getId());
        view.displayMeetings(meetings);
    } // Method 'displayCalendar' end --

    // Method 'logout':                     // Method for user logout
    private void logout() {
        isLoggedIn = false;
        view.displayMessage("You are logged out.");
    } // Method 'logout' end --

    // Method 'exit':                       // Method for terminating the application
    private void exit() {
        try {
            System.exit(0);         // Terminates the running JVM and exits the program
        } catch(SecurityException e) {
            view.displayErrorMessage("JVM failed to terminate!");
            e.printStackTrace();
        }
    } // Method 'exit' end --

} // Class end --