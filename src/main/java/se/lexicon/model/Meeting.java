package se.lexicon.model;

import java.time.LocalDateTime;

public class Meeting {
// --- Fields ---
    // --- Instance Fields ---
    private int id;                     // Auto generated in DB (auto_increment)
    private String title;
    private LocalDateTime startTime;    // 2024-09-02 09:00
    private LocalDateTime endTime;      // 2024-09-01 09:00 (Before startTime -> Needs to throw IllegalArgument!)
    private String description;
    private Calendar calendar;


// --- Constructors ---
    // Constructor 'Meeting':           - Used for taking data from the user (via console) to create a meeting
    public Meeting(String title, LocalDateTime startTime, LocalDateTime endTime, String description) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    } // Constructor 'Meeting' end --

    // Constructor 'Meeting':           - Used for saving meeting data to the database
    public Meeting(String title, LocalDateTime startTime, LocalDateTime endTime, String description, Calendar calendar) {
        this(title, startTime, endTime, description);
        this.calendar = calendar;
    } // Constructor 'Meeting' end --

    // Constructor 'Meeting':           - Used for fetching meeting data from the database
    public Meeting(int id, String title, LocalDateTime startTime, LocalDateTime endTime, String description, Calendar calendar) {
        this(title, startTime, endTime, description);
        this.id = id;
        this.calendar = calendar;
    } // Constructor 'Meeting' end --

    // Constructor 'Meeting':           - Used for returning all meeting information without the calendar
    public Meeting(int id, String title, LocalDateTime startTime, LocalDateTime endTime, String description) {
        this(title, startTime, endTime, description);
        this.id = id;
    } // Constructor 'Meeting' end --


// --- Setters & Getters ---
    // --- Setters ---              // Enables outside writing to the private variables of this class

    // Setter 'setCalendar':
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    } // Setter Method 'setCalendar' end --


    // --- Getters ---              // Enables outside reading of the private variables of this class

    // Getter 'getId':
    public int getId() {
        return id;
    } // Getter Method 'getId' end --

    // Getter 'getTitle':
    public String getTitle() {
        return title;
    } // Getter Method 'getTitle' end --

    // Getter 'getStartTime':
    public LocalDateTime getStartTime() {
        return startTime;
    } // Getter Method 'getStartTime' end --

    // Getter 'getEndTime':
    public LocalDateTime getEndTime() {
        return endTime;
    } // Getter Method 'getEndTime' end --

    // Getter 'getDescription':
    public String getDescription() {
        return description;
    } // Getter Method 'getDescription' end --

    // Getter 'getCalendar':
    public Calendar getCalendar() {
        return calendar;
    } // Getter Method 'getCalendar' end --


// --- Methods ---
    // Method 'timeValidation':
    public void timeValidation() {
        // Check if start time is before now:
        LocalDateTime now = LocalDateTime.now();
        if(this.startTime.isBefore(now)) {
            throw new IllegalArgumentException("Start time must be in the future.");
        }
        // Check if end time is before start time:
        if(this.endTime.isBefore(this.startTime)) {
            throw new IllegalArgumentException("End time must be after the start time.");
        }
    } // Method 'timeValidation' end --

    // Method 'meetingInfo':
    public String meetingInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Meeting info:").append("\n");
        stringBuilder.append("Id: ").append(id).append("\n");
        stringBuilder.append("Title: ").append(title).append("\n");
        stringBuilder.append("Start time: ").append(startTime).append("\n");
        stringBuilder.append("End time: ").append(endTime).append("\n");
        stringBuilder.append("Description: ").append(description).append("\n");
        stringBuilder.append("Calendar title: ").append(calendar.getTitle()).append("\n");
        return stringBuilder.toString();
    } // Method 'meetingInfo' end --

} // Class end --