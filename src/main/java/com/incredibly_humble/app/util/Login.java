package com.incredibly_humble.app.util;

import com.incredibly_humble.app.util.impl.exceptions.TriesExceededException;

/**
 * an interface to facilitate login
 */
public interface Login {
    /**
     * Method that takes in a username and password and will throw an error if there were three failed attempts
     * on this specific username
     * @param username - username for logging in
     * @param password - associated password
     * @return a boolean whether login was successful or not
     * @throws TriesExceededException - thrown after three failed attempts.
     */
    public boolean verify(String username, String password) throws TriesExceededException;
}
