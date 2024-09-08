package se.lexicon.model;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
// --- Fields ---
    // --- Instance Fields ---
    private int id;
    private String title;
    private List<Meeting> meetings;
    private String username;


// --- Constructors ---
    // Constructor 'Calendar':
    public Calendar(String title, String username) {
        this.title = title;
        this.username = username;
    } // Constructor 'Calendar' end --

    // Constructor 'Calendar':
    public Calendar(int id, String title, String username) {
        this(title, username);
        this.id = id;
    } // Constructor 'Calendar' end --


// --- Setters & Getters ---
    // --- Getters ---              // Enables outside reading of the private variables of this class

    // Getter 'getId':
    public int getId() {
        return id;
    } // Getter Method 'getId' end --

    // Getter 'getTitle':
    public String getTitle() {
        return title;
    } // Getter Method 'getTitle' end --

    // Getter 'getMeetings':
    public List<Meeting> getMeetings() {
        if(meetings == null) {
            meetings = new ArrayList<>();
        }
        return meetings;
    } // Getter Method 'getMeetings' end --

    // Getter 'getUsername':
    public String getUsername() {
        return username;
    } // Getter Method 'getUsername' end --


// --- Methods ---
    // Method 'addMeeting':
    public void addMeeting(Meeting meeting) {
        if(meetings == null){
            meetings = new ArrayList<>();
        }
        // todo: Check if meeting exists in meetings list

        meetings.add(meeting);
    } // Method 'addMeeting' end --

    // Method 'removeMeeting':
    public void removeMeeting(Meeting meeting) {
        if(meetings == null) throw new IllegalArgumentException("Meeting list is null.");
        if(meeting == null) throw new IllegalArgumentException("Meeting data is null.");
        meetings.remove(meeting);
    } // Method 'removeMeeting' end --

    // Method 'calendarInfo':
    public String calendarInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Calendar info:").append("\n");
        stringBuilder.append("Id: ").append(id).append("\n");
        stringBuilder.append("Title: ").append(title).append("\n");
        stringBuilder.append("Username: ").append(username).append("\n");
        return stringBuilder.toString();
    } // Method 'calendarInfo' end --

} // Class end --