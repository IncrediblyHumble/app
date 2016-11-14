package com.incredibly_humble.app.util.impl;

import com.incredibly_humble.app.util.Login;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;
import com.incredibly_humble.app.util.impl.exceptions.TriesExceededException;

/**
 * An implementation of Login that uses the hardcoded username "foo" and password "bar"
 */
public class LoginHardcoded implements Login{
    static int count = 0;
    /**
     * verifies if user exists
     * @param username name of user
     * @param password password to login
     * @throws TriesExceededException if person logs in incorrectly 3 times
     */
    public boolean verify(String username, String password) throws DatabaseException {
        if(username.equals("foo") && password.equals("bar") && (count < 3)){
            count = 0;
            return true;
        }
        else {
            count++;
            if(count >= 3){
                throw new DatabaseException("");
            }
            return false;
        }
    }
}
