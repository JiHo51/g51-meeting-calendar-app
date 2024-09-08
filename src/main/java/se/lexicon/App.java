package se.lexicon;

import se.lexicon.controller.CalendarController;
import se.lexicon.dao.CalendarDAO;
import se.lexicon.dao.MeetingDAO;
import se.lexicon.dao.UserDAO;
import se.lexicon.dao.db.CalendarDBConnection;
import se.lexicon.dao.impl.CalendarDAOImpl;
import se.lexicon.dao.impl.MeetingDAOImpl;
import se.lexicon.dao.impl.UserDAOImpl;
import se.lexicon.exception.CalendarExceptionHandler;
import se.lexicon.model.User;
import se.lexicon.view.CalendarView;
import se.lexicon.view.CalendarViewImpl;

import java.sql.Connection;
import java.util.Optional;

/**
 * Meeting Calendar application
 *
 */
public class App {
    // Method 'main':
    public static void main( String[] args ) {
        try {
            // Open connection to DB:
            Connection connection = CalendarDBConnection.getConnection();

            // Instantiate 'CalendarViewImpl' to object 'view':
            CalendarView view = new CalendarViewImpl();

            // Instantiate 'UserDAOImpl' to object 'userDAO':
            UserDAO userDAO = new UserDAOImpl(connection);
            //
            //User userCreated = userDAO.createUser("admin");
            //System.out.println("User info: " + userCreated.userInfo());
            Optional<User> userOptional = userDAO.findByUsername("admin");
            if(userOptional.isPresent()) {
                System.out.println("Hashed password: " + userOptional.get().getHashedPassword());
            }

            // Instantiate 'CalendarDAOImpl' to object 'calendarDAO':
            CalendarDAO calendarDAO = new CalendarDAOImpl(connection);
            // Instantiate 'MeetingDAOImpl' to object 'meetingDAO':
            MeetingDAO meetingDAO = new MeetingDAOImpl(connection);
            // Instantiate 'CalendarController' to object 'controller':
            CalendarController controller = new CalendarController(view, userDAO, calendarDAO, meetingDAO);
            // Call "Run application": Display Menu - execute methods by user choice
            controller.run();
        } catch(Exception e) {
            CalendarExceptionHandler.handleException(e);
        }
    } // Method 'main' end --

} // Class end --