package se.lexicon.view;

import se.lexicon.model.Calendar;
import se.lexicon.model.Meeting;
import se.lexicon.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CalendarViewImpl implements CalendarView {
// --- Methods ---
    // Method 'displayUser':                // Display user information
    @Override
    public void displayUser(User user) {
        System.out.println(user.userInfo());
        System.out.println("------------------------------------");
    } // Method 'displayUser' end --

    // Method 'displayCalendar':            // Display calendar information
    @Override
    public void displayCalendar(Calendar calendar) {
        System.out.println(calendar.calendarInfo());
        System.out.println("------------------------------------");
    } // Method 'displayCalendar' end --

    // Method 'displayMeetings':            // Display information all meetings
    @Override
    public void displayMeetings(List<Meeting> meetings) {
        if(meetings.isEmpty()) {
            System.out.println("No meetings in this calendar.");
        } else {
            System.out.println("Meetings in this calendar:");
            meetings.forEach(meeting -> {
                System.out.println(meeting.meetingInfo());
                System.out.println("------------------------------------");
            });
        }
    } // Method 'displayMeetings' end --

    // Method 'promoteString':              // User input: string (this line, then start in next line)
    @Override
    public String promoteString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();          // Return user input: current line excl. line separator, start in next line
    } // Method 'promoteString' end --

    // Method 'promoteUserForm':            // User input: User Form = username & password
    @Override
    public User promoteUserForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a username:");
        String username = scanner.nextLine();

        System.out.println("Enter a password:");
        String password = scanner.nextLine();
        return new User(username, password);
    } // Method 'promoteUserForm' end --

    // Method 'promoteMeetingForm':         // User input: Meeting Form = title, startDateTime, endDateTime, description
    @Override
    public Meeting promoteMeetingForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a title for meeting:");
        String title = scanner.nextLine();

        System.out.println("Start Date & Time (yyyy-MM-dd HH:mm):");
        String start = scanner.nextLine();

        System.out.println("End Date & Time (yyyy-MM-dd HH:mm):");
        String end = scanner.nextLine();

        System.out.println("Enter Description:");
        String description = scanner.nextLine();

        // Parse and format the String (start, end) into LocalDateTime pattern:
        LocalDateTime startDateTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        // Create Meeting object with retrieved data:
        return new Meeting(title, startDateTime, endDateTime, description);
    } // Method 'promoteMeetingForm' end --

    // Method 'promoteCalendarForm':        // User input: Calendar Form = title
    @Override
    public String promoteCalendarForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a title for calendar:");
        return scanner.nextLine();
    } // Method 'promoteCalendarForm' end --

} // Class end --