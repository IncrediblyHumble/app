package com.incredibly_humble.app.util.impl;

import com.incredibly_humble.app.util.Login;

/**
 * An implementation of Login that uses the hardcoded username "foo" and password "bar"
 * Created by noam on 9/25/16.
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
