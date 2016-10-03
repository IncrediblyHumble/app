package com.incredibly_humble.app.models;

public class User extends Customer {

    private String name;

        private User(String name) {
            super(name,null);
        }

        public String getName() {
            return name;
        }
        private String email = "";
        private String phone = "";
        private boolean subscribed;
        private String address = "";

        /**
         * @return the email
         */
        public String getEmail() {
            return email;
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
}
