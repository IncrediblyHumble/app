package com.incredibly_humble.app.util.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.incredibly_humble.app.util.HttpClient;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;
import com.incredibly_humble.models.*;
import com.incredibly_humble.app.util.Database;

import java.util.ArrayList;

/**
 * a class containing the remote database
 */
@SuppressWarnings("ConstantConditions")
public class RemoteDatabase implements Database {
    private final String USER_AGENT = "Mozilla/5.0";
    private final String base_url = "http://localhost:4567/";
    private User currentUser;
    Gson gson;
    @Inject
    private HttpClient httpClient;
    /**
     * a method that builds the database
     */
    public RemoteDatabase() {
        gson = new GsonBuilder().create();
        httpClient = new HttpClientImpl();
    }
    /**
     * adds a user to the database
     * @param u the user to add
     * @return the user that has been added
     * @throws DatabaseException if the info is not inputted correctly
     */
    @Override
    public User addUser(User u) throws DatabaseException {
        return userSendingHttpRequest("addUser", u);
    }

    /**
     * updates the user information
     * @param u user to be updated
     * @return the user that was updated
     * @throws DatabaseException if user is not found
     */
    @Override
    public User updateUser(User u) throws DatabaseException {
        return userSendingHttpRequest("updateUser", u);
    }

    private User userSendingHttpRequest(String path, User u) throws DatabaseException {
        String res = httpClient.post(this.base_url + path, u);
        User user = gson.fromJson(res, User.class);
        if (user.getEmail() == null) {
            throw new DatabaseException(user.getName());
        }
        return user;
    }

   /**
     * deletes a user from database
     * @param u the user to be deleted
     * @return the deleted user
     * @throws DatabaseException if any issues
     */
    @Override
    public User deleteUser(User u) throws DatabaseException {
        return null;
    }
    /**
     * checks the login information
     * @param email the user email
     * @param pass the password to be searched for
     * @return if valid login info or not
     * @throws DatabaseException if input is null
     */
    @Override
    public boolean checkCredentialsAndLogin(String email, String pass) throws DatabaseException {
        this.currentUser = userSendingHttpRequest("login", new User(null, email, pass, null));
        return true;
    }
    /**
     * gets the users from database
     * @return a list of users
     */
    @Override
    public ArrayList<User> getUsers() {
        return null;
    }
    /**
     * gets the current user info
     * @return info of the current user
     */
    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }
    /**
     * adds a source report to the database
     * @param report the report to be added
     * @return the added report
     */
    @Override
    public WaterSourceReport addWaterSourceReport(WaterSourceReport report) {
        String res = this.httpClient.post(base_url + "addWaterSourceReport", report);
        return gson.fromJson(res, WaterSourceReport.class);
    }
    /**
     * gets a list of source reports
     * @return the list of source reports
     */
    @Override
    public ArrayList<WaterSourceReport> getWaterSourceReports() {
        String res = this.httpClient.get(base_url + "getWaterSourceReports");
        WaterSourceReports reports = gson.fromJson(res, WaterSourceReports.class);
        return reports.getReports();

    }
    /**
     * logs the current user out of the application
     */
    @Override
    public void logout() {
        this.currentUser = null;
    }

    /**
     * deletes a source report
     * @param report the report to be deleted
     */
    @Override
    public void deleteWaterSourceReport(WaterSourceReport report) {
        httpClient.post(base_url + "deleteWaterSourceReport", report);
    }
    /**
     * adds a quality report to the database
     * @param report the report to be added
     * @return the added report
     */
    @Override
    public WaterQualityReport addWaterQualityReport(WaterQualityReport report) {
        String res = httpClient.post(base_url + "addWaterQualityReport", report);
        return gson.fromJson(res, WaterQualityReport.class);
    }
    /**
     * gets a list of quality reports
     * @return the list of quality reports
     */
    @Override
    public ArrayList<WaterQualityReport> getWaterQualityReports() {
        String res = httpClient.get(base_url + "getWaterQualityReports");
        WaterQualityReports reports = gson.fromJson(res, WaterQualityReports.class);
        return reports.getReports();

    }
    /**
     * deletes a quality report
     * @param r the report to be deleted
     * @return the deleted report
     */
    @Override
    public WaterQualityReport deleteWaterQualityReport(WaterQualityReport r) {
        return null;
    }
}
