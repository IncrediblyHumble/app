package com.incredibly_humble.app.util.impl;

import com.google.gson.Gson;
import com.incredibly_humble.app.util.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientImpl implements HttpClient{
    Gson gson;

    public HttpClientImpl() {
        gson = new Gson();
    }

    public String post(String url, Object param) {
        try {
            URL u = new URL(url);
            HttpURLConnection con = (HttpURLConnection) u.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");

            // Send post request
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(gson.toJson(param));
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String res = readResponse(in);
            in.close();
            System.out.println(res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String get(String url){
        try {
            URL r = new URL(url);
            HttpURLConnection con = (HttpURLConnection) r.openConnection();

            //add reuqest header
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String res = readResponse(in);
            in.close();
            System.out.println(res);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readResponse(BufferedReader in) throws IOException {
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        return response.toString();
    }
}
