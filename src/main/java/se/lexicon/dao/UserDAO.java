package se.lexicon.dao;

import se.lexicon.exception.AuthenticationFailedException;
import se.lexicon.exception.UserExpiredException;
import se.lexicon.model.User;

import java.util.Optional;

public interface UserDAO {                          // [Interface: Always 'abstract']
// --- Methods ---                                  [Methods: Always 'public' in Interface]
    // --- Abstract Methods --- [Enforce SubClasses to implement these Methods (abstract)]

    // Method 'createUser':
    User createUser(String username);

    // Method 'findByUsername':
    Optional<User> findByUsername(String username);     // Optional for using validation methods?

    // Method 'authenticate':
    boolean authenticate(User user)                     // If deleted returns true
            throws AuthenticationFailedException, UserExpiredException;


    // --- Default Methods ---

    // Method 'methodName2':

    // Method 'methodName2' end --


    // --- Static Methods ---

    // Method 'methodName3':

    // Method 'methodName3' end --

} // Interface end --