package com.incredibly_humble.app.util;

import com.incredibly_humble.app.models.User;

/**
 * interface for handling database interactions
 */
public interface Database {
    public void addUser(User u);
    public void updateUser(User u);
    public void deleteUser(User u);
    public void getUsers();
}
