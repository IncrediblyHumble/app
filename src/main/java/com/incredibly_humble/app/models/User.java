package com.incredibly_humble.app.models;

public class User {

    enum AccountType {
        USER,
        WORKER,
        MANAGER,
        ADMIN
    }

    private String name;
    private String password;
    private String email = "";
    private String phone = "";
    private boolean subscribed;
    private String address = "";
    AccountType type;
    public User(String name, String password, AccountType type) {
        this.name = name;
        this.password = password;
        this.type = type;
    }


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
     *
     * @return password
     */
    public String getPassword(){
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
    @Override
    public boolean equals(Object comparison) {
        if (comparison instanceof User) {
            User foo = (User) comparison;
            if (foo.getName().equals(this.name) && foo.getPassword().equals(this.password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
