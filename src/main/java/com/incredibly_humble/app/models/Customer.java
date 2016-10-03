package com.incredibly_humble.app.models;

public class Customer {
    private String name;
    private String password;

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object comparison) {
        if (comparison instanceof Customer) {
            Customer foo = (Customer) comparison;
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
