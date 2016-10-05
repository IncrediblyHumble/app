package com.incredibly_humble.app.util.impl;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.Login;
import com.incredibly_humble.app.util.impl.exceptions.TriesExceededException;

public class DatabaseLogin implements Login{
    @Inject
    Database db;
    /**
     * @return the boolean if person is in database
     */
    @Override
    public boolean verify(String username, String password) throws TriesExceededException {
        return db.checkCredentialsAndLogin(username, password);
    }
}
