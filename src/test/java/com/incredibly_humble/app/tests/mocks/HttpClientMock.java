package com.incredibly_humble.app.tests.mocks;

import com.google.gson.Gson;
import com.incredibly_humble.app.util.HttpClient;

public class HttpClientMock implements HttpClient {
    public String url;
    public Object param;
    public String call;
    Gson gson;
    public HttpClientMock(){
        gson = new Gson();
    }
    @Override
    public String post(String url, Object param) {
        this.url = url;
        this.param = param;
        this.call = "POST";
        return "{}";
    }

    @Override
    public String get(String url) {
        this.url = url;
        this.param = null;
        this.call = "GET";
        return "{}";
    }
}
