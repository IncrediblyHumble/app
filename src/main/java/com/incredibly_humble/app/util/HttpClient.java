package com.incredibly_humble.app.util;

public interface HttpClient {
    public String post(String url, Object param);
    public String get(String url);
}
