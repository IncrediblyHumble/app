package com.incredibly_humble.app.util;

import com.incredibly_humble.models.User;
import com.incredibly_humble.models.WaterQualityReport;
import com.incredibly_humble.models.WaterSourceReport;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;

import java.util.ArrayList;

/**
 * interface for handling database interactions
 */
public interface Database {
    /**
     * adds a user to the database
     *
     * @param u a new user
     * @return the added user
     * @throws DatabaseException
     */
    public User addUser(User u) throws DatabaseException;

    /**
     * updates the user data
     *
     * @param u the updated user
     * @return the updated user
     * @throws DatabaseException
     */
    public User updateUser(User u) throws DatabaseException;

    /**
     * deletes a user from the database
     *
     * @param u the user data to be deleted
     * @return the user which was deleted
     * @throws DatabaseException
     */
    public User deleteUser(User u) throws DatabaseException;

    /**
     * checks to see if inputted login info is valid in the database
     *
     * @param user the username to be searched for
     * @param pass the password to be searched for
     * @return true if the username/password were found
     */
    public boolean checkCredentialsAndLogin(String user, String pass)throws DatabaseException;

    /**
     * gets a list of all of the users in the database
     *
     * @return an arrayList of the users
     */
    public ArrayList<User> getUsers();

    /**
     * checks to see who is currently using the program
     *
     * @return the current user data
     */
    public User getCurrentUser();

    /**
     * adds a water report to the WaterSourceReports table
     * @param report
     * @return
     */
    public WaterSourceReport addWaterSourceReport(WaterSourceReport report);

    /**
     * Gets all the water reports as an ArrayList
     * @return
     */
    public ArrayList<WaterSourceReport> getWaterSourceReports();

    /**
     * Logs the user out.
     */
    public void logout();

    /**
     * deletes a report
     * @param report the report to be deleted
     */
    public void deleteWaterSourceReport(WaterSourceReport report);

    /**
     * add report
     * @param report report to be added
     * @return the report that's been added
     */
    public WaterQualityReport addWaterQualityReport(WaterQualityReport report);

    /**
     * gets reports
     * @return a list of reports
     */
    public ArrayList<WaterQualityReport> getWaterQualityReports();

    /**
     * deletes a report
     * @param r the report to be deleted
     * @return the thats been deleted
     */
    public WaterQualityReport deleteWaterQualityReport(WaterQualityReport r);


}
