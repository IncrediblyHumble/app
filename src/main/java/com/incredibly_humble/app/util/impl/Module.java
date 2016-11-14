package com.incredibly_humble.app.util.impl;

import com.google.inject.AbstractModule;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.HttpClient;
import com.incredibly_humble.app.util.Login;

import java.net.HttpURLConnection;
/**
 * a class representing the module
 */
public class Module extends AbstractModule {
    /**
     * binds instances of login, ScreenSwitch and memory
     */
    @Override
    protected void configure() {
        bind(Login.class).to(DatabaseLogin.class);
        bind(ScreenSwitch.class);
        bind(Database.class).toInstance(new RemoteDatabase());
        bind(HttpClient.class).toInstance(new HttpClientImpl());
    }
}
