package se.lexicon.dao;

import se.lexicon.model.Calendar;

import java.util.Collection;
import java.util.Optional;

public interface CalendarDAO {                      // [Interface: Always 'abstract']
// --- Methods ---                                  [Methods: Always 'public' in Interface]
    // --- Abstract Methods --- [Enforce SubClasses to implement these Methods (abstract)]

    // Method 'createCalendar':
    Calendar createCalendar(String title, String username);

    // Method 'findById':
    Optional<Calendar> findById(int id);

    // Method 'findCalendarByUserName':
    Collection<Calendar> findCalendarByUserName(String username);

    // Method 'findByTitleAndUsername':
    Optional<Calendar> findByTitleAndUsername(String title, String username);

    // Method 'deleteCalendar':
    boolean deleteCalendar(int id);                 // If deleted returns true

} // Interface end --