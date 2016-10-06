package com.incredibly_humble.app.util;

import com.incredibly_humble.app.models.User;
import com.incredibly_humble.app.models.WaterReport;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;

import java.util.ArrayList;

/**
 * interface for handling database interactions
 */
public interface Database {
    /**
     * adds a user to the database
     * @param u a new user
     * @return the added user
     * @throws DatabaseException
     */
    public User addUser(User u) throws DatabaseException;

    /**
     * updates the user data
     * @param u the updated user
     * @return the updated user
     * @throws DatabaseException
     */
    public User updateUser(User u) throws DatabaseException;

    /**
     * deletes a user from the database
     * @param u the user data to be deleted
     * @return the user which was deleted
     * @throws DatabaseException
     */
    public User deleteUser(User u) throws DatabaseException;

    /**
     * checks to see if inputted login info is valid in the database
     * @param user the username to be searched for
     * @param pass the password to be searched for
     * @return true if the username/password were found
     */
    public boolean checkCredentialsAndLogin(String user, String pass);

    /**
     * gets a list of all of the users in the database
     * @return an arrayList of the users
     */
    public ArrayList<User> getUsers();

    /**
     * checks to see who is currently using the program
     * @return the current user data
     */
    public User getCurrentUser();

    public WaterReport addWaterReport(WaterReport report);
}
