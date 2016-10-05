package com.incredibly_humble.app.util.impl;

import com.incredibly_humble.app.util.Login;
import com.incredibly_humble.app.util.impl.exceptions.TriesExceededException;

/**
 * An implementation of Login that uses the hardcoded username "foo" and password "bar"
 */
public class LoginHardcoded implements Login{
    static int count = 0;
    public boolean verify(String username, String password) throws TriesExceededException {
        if(username.equals("foo") && password.equals("bar") && (count < 3)){
            count = 0;
            return true;
        }
        else {
            count++;
            if(count >= 3){
                throw new TriesExceededException();
            }
            return false;
        }
    }
}
