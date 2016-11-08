package com.incredibly_humble.app.util.impl;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.Login;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;
import com.incredibly_humble.app.util.impl.exceptions.TriesExceededException;

/**
 * a class to login login
 */
public class DatabaseLogin implements Login{
    @Inject
    Database db;
    /**
     * @param username name of user
     * @param password password to login user
     * @throws TriesExceededException if login fails 3 times
     * @return the boolean if person is in database
     */
    @Override
    public boolean verify(String username, String password) throws DatabaseException {
        return db.checkCredentialsAndLogin(username, password);
    }

}
