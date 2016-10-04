package com.incredibly_humble.app.util.impl;

import com.google.inject.Singleton;
import com.incredibly_humble.app.models.User;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.HashMap;

@Singleton
public class InMemoryDatabase implements Database {
    private HashMap<String, User> users;
    private User loggedIn;
    public InMemoryDatabase(){
        users = new HashMap<String,User>();
    }
    @Override
    public User addUser(User u) throws DatabaseException {
        if(users.containsKey(u.getName())){
            throw new DatabaseException("User already exists");
        }
        return users.put(u.getName(), u);
    }

    @Override
    public User updateUser(User u) throws DatabaseException{
        if(!users.containsKey(u.getName())){
            throw new DatabaseException("User Does Not Exist");
        }
        users.remove(u.getName());
        return users.put(u.getName(),u);
    }

    @Override
    public User deleteUser(User u) throws DatabaseException {
        if(!users.containsKey(u.getName())){
            throw new DatabaseException("User Does Not Exist");
        }
        return users.remove(u.getName());
    }

    @Override
    public boolean checkCredentialsAndLogin(String user, String pass) {
        if(users.containsKey(user) && users.get(user).getPassword().equals(pass)){
            loggedIn = users.get(user);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<User> getUsers() {
        return new ArrayList<User>(users.values());
    }

    @Override
    public User getCurrentUser() {
        return loggedIn;
    }


}
