package com.incredibly_humble.app.util.impl;

import com.google.inject.AbstractModule;
import com.incredibly_humble.app.util.Login;
import com.incredibly_humble.app.util.impl.LoginHardcoded;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(Login.class).to(LoginHardcoded.class);
    }
}
