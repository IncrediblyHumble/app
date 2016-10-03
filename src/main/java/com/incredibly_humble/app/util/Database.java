package com.incredibly_humble.app.util;

import com.incredibly_humble.app.models.Customer;

/**
 * interface for handling database interactions
 */
public interface Database {
    public void addUser(Customer c);
    public void updateUser(Customer c);
    public void deleteUser(Customer c);
    public void getUsers();
}
