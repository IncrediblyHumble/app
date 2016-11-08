package com.incredibly_humble.models;

import java.io.Serializable;

/**
 * a class representing the user
 */
public class User implements Serializable {

    /**
     * a subclass enum representing the type of account
     */
    public static enum AccountType {
        USER,
        WORKER,
        MANAGER,
        ADMIN
    }

    private String name;
    private String password;
    private String email = "";
    AccountType type;
    private boolean subscribed;
    private String address = "";
    private String phone = "";

    /**
     * user constructor
     * @param name name of user
     * @param email email of user
     * @param password user password
     * @param type type of account
     */
    public User(String name, String email, String password, AccountType type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    /**
     * a constructor for user that asks for subscription
     * @param name name of user
     * @param email user email
     * @param password user password
     * @param type user type
     * @param subscribed is subscribed or not
     * @param address user address
     * @param phone user phine number
     */
    public User(String name, String email, String password, AccountType type,
                boolean subscribed, String address, String phone) {
        this(name, email, password, type);
        this.phone = phone;
        this.subscribed = subscribed;
        this.address = address;
    }

    /**
     * @return the username of user
     */
    public String getName() {
        return name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the subscribed
     */
    public boolean isSubscribed() {
        return subscribed;
    }

    /**
     * @param subscribed the subscribed to set
     */
    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * gets type of account
     * @return account type
     */
    public AccountType getType() {
        return this.type;
    }

    /**
     * is subscribed or not
     * @return subscribed boolean
     */
    public boolean getSubscribed(){
        return this.subscribed;
    }

    /**
     * tells if users are equal
     * @param comparison the users to be compared
     * @return boolean whether equal or not
     */
    @Override
    public boolean equals(Object comparison) {
        if (comparison instanceof User) {
            User foo = (User) comparison;
            return (foo.getName().equals(this.name) && foo.getPassword().equals(this.password));
        } else {
            return false;
        }
    }

}
