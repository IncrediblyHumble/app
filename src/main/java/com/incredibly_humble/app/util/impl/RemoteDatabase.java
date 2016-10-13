package com.incredibly_humble.app.util.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;
import com.incredibly_humble.models.User;
import com.incredibly_humble.models.WaterReport;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.models.WaterReports;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class RemoteDatabase implements Database {
    private final String USER_AGENT = "Mozilla/5.0";
    private final String base_url = "http://localhost:4567/";
    private User currentUser;

    Gson gson;

    public RemoteDatabase() {
        gson = new GsonBuilder().create();
    }

    @Override
    public User addUser(User u) throws DatabaseException {
        User user = userSendingHttpRequest("addUser", u);
        System.out.println(user.getName());
        if (user.getEmail() == null) {
            throw new DatabaseException(user.getName());
        }
        return user;
    }


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

    @Override
    public User deleteUser(User u) throws DatabaseException {
        return null;
    }

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

    @Override
    public ArrayList<User> getUsers() {
        return null;
    }

    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public WaterReport addWaterReport(WaterReport report) {
        try {
            URL url = new URL(base_url + "addWaterReport");
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
            WaterReport waterReport = gson.fromJson(readResponse(in), WaterReport.class);
            in.close();
            return waterReport;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<WaterReport> getWaterReports() {
        try {
            URL url = new URL(base_url + "getWaterReports");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //add reuqest header
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            WaterReports reports = gson.fromJson(readResponse(in), WaterReports.class);
            in.close();
            return reports.getReports();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void logout() {
        this.currentUser= null;
    }
}
