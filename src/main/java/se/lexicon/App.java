package se.lexicon;

import se.lexicon.controller.CalendarController;
import se.lexicon.dao.CalendarDAO;
import se.lexicon.dao.MeetingDAO;
import se.lexicon.dao.UserDAO;
import se.lexicon.dao.db.CalendarDBConnection;
import se.lexicon.dao.impl.CalendarDAOImpl;
import se.lexicon.dao.impl.MeetingDAOImpl;
import se.lexicon.dao.impl.UserDAOImpl;
import se.lexicon.view.CalendarView;
import se.lexicon.view.CalendarViewImpl;

import java.sql.Connection;

/**
 * Meeting Calendar application
 *
 */
public class App {
    // Method 'main':
    public static void main( String[] args ) {
        // Instantiate 'CalendarViewImpl' to object 'view':
        CalendarView view = new CalendarViewImpl();
        // Open connection to DB:
        Connection connection = CalendarDBConnection.getConnection();
        // Instantiate 'UserDAOImpl' to object 'userDAO':
        UserDAO userDAO = new UserDAOImpl(connection);
        // Instantiate 'CalendarDAOImpl' to object 'calendarDAO':
        CalendarDAO calendarDAO = new CalendarDAOImpl(connection);
        // Instantiate 'MeetingDAOImpl' to object 'meetingDAO':
        MeetingDAO meetingDAO = new MeetingDAOImpl(connection);
        // Instantiate 'CalendarController' to object 'controller':
        CalendarController controller = new CalendarController(view, userDAO, calendarDAO, meetingDAO);
        // Call "Run application": Display Menu - execute methods by user choice
        controller.run();
    } // Method 'main' end --

} // Class end --