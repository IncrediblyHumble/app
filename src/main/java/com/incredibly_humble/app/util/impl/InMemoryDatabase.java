package com.incredibly_humble.app.util.impl;

import com.google.inject.Singleton;
import com.incredibly_humble.app.models.User;
import com.incredibly_humble.app.models.WaterReport;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.HashMap;

@Singleton
public class InMemoryDatabase implements Database {
    private HashMap<String, User> users;
    private HashMap<Integer, WaterReport> waterReports;
    private User loggedIn;
    private int waterReportId = 0;
    public InMemoryDatabase(){
        users = new HashMap<String,User>();
        waterReports = new HashMap<Integer, WaterReport>();
    }
    /**
     * @return the user we just added into database
     */
    @Override
    public User addUser(User u) throws DatabaseException {
        if(users.containsKey(u.getName())){
            throw new DatabaseException("User already exists");
        }
        return users.put(u.getName(), u);
    }
    /**
     * @return the user we
     * @throws DatabaseException
     */
    @Override
    public User updateUser(User u) throws DatabaseException{
        if(!users.containsKey(u.getName())){
            throw new DatabaseException("User Does Not Exist");
        }
        users.remove(u.getName());
        return users.put(u.getName(),u);
    }
    /**
     * @return the user we deleted
     * @throws DatabaseException
     */
    @Override
    public User deleteUser(User u) throws DatabaseException {
        if(!users.containsKey(u.getName())){
            throw new DatabaseException("User Does Not Exist");
        }
        return users.remove(u.getName());
    }
    /**
     * @param user name of user id
     * @param pass password to login
     * @return true if login username and password exists
     */
    @Override
    public boolean checkCredentialsAndLogin(String user, String pass) {
        if(users.containsKey(user) && users.get(user).getPassword().equals(pass)){
            loggedIn = users.get(user);
            return true;
        }
        return false;
    }
    /**
     * @return ArrayList<User> of users in the database
     */
    @Override
    public ArrayList<User> getUsers() {
        return new ArrayList<User>(users.values());
    }
    /**
     * @return User current users in the database
     */
    @Override
    public User getCurrentUser() {
        return loggedIn;
    }

    @Override
    public WaterReport addWaterReport(WaterReport report) {
        report.setId(checkoutWaterReportId());
        report.setWorkerName(loggedIn.getName());
        waterReports.put(report.getId(), report);
        return report;
    }

    private int checkoutWaterReportId(){
        return waterReportId++;
    }
    public ArrayList<WaterReport> getWaterReports(){
        return new ArrayList<WaterReport>(waterReports.values());
    }

}
