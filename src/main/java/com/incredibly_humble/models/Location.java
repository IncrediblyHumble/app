package com.incredibly_humble.models;

/**
 * a class representing locations
 */
public class Location {
    private double longitude;
    private double latitude;

    /**
     * constructor for location
     * @param latitude the latitude of the location
     * @param longitude the longitude of location
     */
    public Location(double latitude, double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * getter for location longitude
     * @return the longitude of the location
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * getter for location latitude
     * @return the latitude of the location
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * turns location into a string
     * @return a string of the location
     */
    public String toString(){
        return String.format("<%f, %f>",this.latitude, this.longitude);
    }
}
