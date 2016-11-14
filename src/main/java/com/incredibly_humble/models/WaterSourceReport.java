package com.incredibly_humble.models;

import java.util.Date;

/**
 * class representing a source report
 */
public class WaterSourceReport {
    /**
     * a subclass representing an enum type of water
     */
    public enum WaterType {
        Bottled,
        Well,
        Stream,
        Lake,
        Spring,
        Other
    }

    private Date dateReported;
    private int id;
    private String workerName;
    private WaterType type;
    private Location location;

    /**
     * constructor of source report
     * @param id report id
     * @param dateReported date of report
     * @param location location of water
     * @param workerName name of creator
     * @param type type of water
     */
    public WaterSourceReport(int id, Date dateReported, Location location, String workerName, WaterType type) {
        this.dateReported = dateReported;
        this.id = id;
        this.workerName = workerName;
        this.location = location;
        this.type = type;
    }

    /**
     * gets date reported
     * @return date reported
     */
    public Date getDateReported() {
        return dateReported;
    }

    /**
     * gets the id of report
     * @return report id
     */
    public int getId() {
        return id;
    }

    /**
     * sets the report id
     * @param id id to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets the creators name
     * @return the name of creator
     */
    public String getWorkerName() {
        return workerName;
    }

    /**
     * sets the name of creator
     * @param name the name to be set of creator
     */
    public void setWorkerName(String name){
        this.workerName = name;
    }

    /**
     * gets the type of water
     * @return water type enum
     */
    public WaterType getType() {
        return type;
    }

    /**
     * gets the location of the water
     * @return the location of the water
     */
    public Location getLocation(){return this.location;}
}
