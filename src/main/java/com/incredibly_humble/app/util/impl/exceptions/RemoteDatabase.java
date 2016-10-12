package com.incredibly_humble.app.util.impl.exceptions;

import com.incredibly_humble.models.User;
import com.incredibly_humble.models.WaterReport;
import com.incredibly_humble.app.util.Database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class RemoteDatabase implements Database{
    private final String USER_AGENT = "Mozilla/5.0";

    @Override
    public User addUser(User u) throws DatabaseException {
        try {

            String url = "http://localhost:4567/hello";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");

            // Send post request
            con.setDoOutput(true);
            ObjectOutputStream wr = new ObjectOutputStream(con.getOutputStream());
            wr.writeObject(u);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUser(User u) throws DatabaseException {
        return null;
    }

    @Override
    public User deleteUser(User u) throws DatabaseException {
        return null;
    }

    @Override
    public boolean checkCredentialsAndLogin(String user, String pass) {
        return false;
    }

    @Override
    public ArrayList<User> getUsers() {
        return null;
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public WaterReport addWaterReport(WaterReport report) {
        return null;
    }

    @Override
    public ArrayList<WaterReport> getWaterReports() {
        return null;
    }

    @Override
    public void logout() {

    }
}
