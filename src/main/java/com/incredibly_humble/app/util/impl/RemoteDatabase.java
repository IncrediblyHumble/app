package com.incredibly_humble.app.util.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;
import com.incredibly_humble.models.*;
import com.incredibly_humble.app.util.Database;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * a class containg the remote database
 */
public class RemoteDatabase implements Database {
    private final String USER_AGENT = "Mozilla/5.0";
    private final String base_url = "http://localhost:4567/";
    private User currentUser;

    Gson gson;

    /**
     * a method that builds the database
     */
    public RemoteDatabase() {
        gson = new GsonBuilder().create();
    }

    /**
     * adds a user to the database
     * @param u the user to add
     * @return the user that has been added
     * @throws DatabaseException if the info is not inputted correctly
     */
    @Override
    public User addUser(User u) throws DatabaseException {
        User user = userSendingHttpRequest("addUser", u);
        System.out.println(user.getName());
        if (user.getEmail() == null) {
            throw new DatabaseException(user.getName());
        }
        return user;
    }

    /**
     * updates the user information
     * @param u user to be updated
     * @return the user that was updated
     * @throws DatabaseException if user is not found
     */
    @Override
    public User updateUser(User u) throws DatabaseException {
        User user = userSendingHttpRequest("updateUser", u);
        if (user.getEmail() == null) {
            throw new DatabaseException(user.getName());
        }
        return user;
    }

    private User userSendingHttpRequest(String path, User u) throws DatabaseException {
        try {
            URL url = new URL(base_url + path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");

            // Send post request
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(gson.toJson(u));
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            User user = gson.fromJson(readResponse(in), User.class);
            in.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readResponse(BufferedReader in) throws Exception {
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        return response.toString();
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
        try {
            this.currentUser = userSendingHttpRequest("login", new User(null, email, pass, null));
            if (this.currentUser.getEmail() == null) {
                throw new DatabaseException(currentUser.getName());
            }
            return true;
        } catch (DatabaseException e) {
            throw e;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
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
        try {
            URL url = new URL(base_url + "addWaterSourceReport");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");

            // Send post request
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(gson.toJson(report));
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            WaterSourceReport waterSourceReport = gson.fromJson(readResponse(in), WaterSourceReport.class);
            in.close();
            return waterSourceReport;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * gets a list of source reports
     * @return the list of source reports
     */
    @Override
    public ArrayList<WaterSourceReport> getWaterSourceReports() {
        try {
            URL url = new URL(base_url + "getWaterSourceReports");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //add reuqest header
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            WaterSourceReports reports = gson.fromJson(readResponse(in), WaterSourceReports.class);
            in.close();
            return reports.getReports();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * logs the current user out of the application
     */
    @Override
    public void logout() {
        this.currentUser= null;
    }

    /**
     * deletes a source report
     * @param report the report to be deleted
     */
    @Override
    public void deleteWaterSourceReport(WaterSourceReport report) {
        try {
            URL url = new URL(base_url + "deleteWaterSourceReport");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");

            // Send post request
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(gson.toJson(report));
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * adds a quality report to the database
     * @param report the report to be added
     * @return the added report
     */
    @Override
    public WaterQualityReport addWaterQualityReport(WaterQualityReport report) {

        try {
            URL url = new URL(base_url + "addWaterQualityReport");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");

            // Send post request
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(gson.toJson(report));
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            WaterQualityReport waterQualityReport = gson.fromJson(readResponse(in), WaterQualityReport.class);
            in.close();
            return waterQualityReport;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * gets a list of quality reports
     * @return the list of quality reports
     */
    @Override
    public ArrayList<WaterQualityReport> getWaterQualityReports() {
        try {
            URL url = new URL(base_url + "getWaterQualityReports");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //add reuqest header
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            WaterQualityReports reports = gson.fromJson(readResponse(in), WaterQualityReports.class);
            in.close();
            System.out.println(reports.getReports().size());
            return reports.getReports();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    }

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
