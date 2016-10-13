package com.incredibly_humble.app.util.impl;

import com.google.inject.AbstractModule;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.Login;

public class Module extends AbstractModule {
    /**
     * binds instances of login, ScreenSwitch and memory
     */
    @Override
    protected void configure() {
        bind(Login.class).to(DatabaseLogin.class);
        bind(ScreenSwitch.class);
        bind(Database.class).toInstance(new RemoteDatabase());
    }
}
