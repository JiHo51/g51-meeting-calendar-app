package se.lexicon.dao;

import se.lexicon.model.Meeting;

import java.util.List;
import java.util.Optional;

public interface MeetingDAO {                       // [Interface: Always 'abstract']
// --- Methods ---                                  [Methods: Always 'public' in Interface]
    // --- Abstract Methods --- [Enforce SubClasses to implement these Methods (abstract)]

    // Method 'createMeeting':
    Meeting createMeeting(Meeting meeting);

    // Method 'findById':
    Optional<Meeting> findById(int meetingId);

    // Method 'findAllMeetingsByCalendarId':        (SELECT * FROM meeting)
    List<Meeting> findAllMeetingsByCalendarId(int calendarId);

    // Method 'deleteMeeting':
    boolean deleteMeeting(int meetingId);       // If deleted returns true

    // todo: Add methods for updating calendars as needed
    // Method 'methodName1':

    // Method 'methodName1' end --


    // --- Default Methods ---

    // Method 'methodName2':

    // Method 'methodName2' end --


    // --- Static Methods ---

    // Method 'methodName3':

    // Method 'methodName3' end --

} // Interface end --