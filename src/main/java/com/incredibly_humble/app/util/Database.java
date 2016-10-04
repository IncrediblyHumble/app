package com.incredibly_humble.app.util;

import com.incredibly_humble.app.models.User;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;

import java.util.ArrayList;

/**
 * interface for handling database interactions
 */
public interface Database {
    public User addUser(User u) throws DatabaseException;

    public User updateUser(User u) throws DatabaseException;

    public User deleteUser(User u) throws DatabaseException;

    public boolean checkCredentials(String user, String pass);

    public ArrayList<User> getUsers();
}
