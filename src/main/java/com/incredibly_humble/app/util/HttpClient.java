package com.incredibly_humble.app.util;

public interface HttpClient {
    String post(String url, Object param);
    String get(String url);
}
