package com.incredibly_humble.app.util.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.incredibly_humble.app.util.HttpClient;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;
import com.incredibly_humble.models.*;
import com.incredibly_humble.app.util.Database;

import java.util.ArrayList;


@SuppressWarnings("ConstantConditions")
public class RemoteDatabase implements Database {
    private final String USER_AGENT = "Mozilla/5.0";
    private final String base_url = "http://localhost:4567/";
    private User currentUser;
    Gson gson;
    @Inject
    private HttpClient httpClient;

    public RemoteDatabase() {
        gson = new GsonBuilder().create();
        httpClient = new HttpClientImpl();
    }

    @Override
    public User addUser(User u) throws DatabaseException {
        return userSendingHttpRequest("addUser", u);
    }


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

    @Override
    public User deleteUser(User u) throws DatabaseException {
        return null;
    }

    @Override
    public boolean checkCredentialsAndLogin(String email, String pass) throws DatabaseException {
        this.currentUser = userSendingHttpRequest("login", new User(null, email, pass, null));
        return true;
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
    public WaterSourceReport addWaterSourceReport(WaterSourceReport report) {
        String res = this.httpClient.post(base_url + "addWaterSourceReport", report);
        return gson.fromJson(res, WaterSourceReport.class);
    }

    @Override
    public ArrayList<WaterSourceReport> getWaterSourceReports() {
        String res = this.httpClient.get(base_url + "getWaterSourceReports");
        WaterSourceReports reports = gson.fromJson(res, WaterSourceReports.class);
        return reports.getReports();

    }

    @Override
    public void logout() {
        this.currentUser = null;
    }

    @Override
    public void deleteWaterSourceReport(WaterSourceReport report) {
        httpClient.post(base_url + "deleteWaterSourceReport", report);
    }

    @Override
    public WaterQualityReport addWaterQualityReport(WaterQualityReport report) {
        String res = httpClient.post(base_url + "addWaterQualityReport", report);
        return gson.fromJson(res, WaterQualityReport.class);
    }

    @Override
    public ArrayList<WaterQualityReport> getWaterQualityReports() {
        String res = httpClient.get(base_url + "getWaterQualityReports");
        WaterQualityReports reports = gson.fromJson(res, WaterQualityReports.class);
        return reports.getReports();

    }

    @Override
    public WaterQualityReport deleteWaterQualityReport(WaterQualityReport r) {
        return null;
    }
}
