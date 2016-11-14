package com.incredibly_humble.models;

import java.util.Date;

/**
 * class representing quality reports
 */
public class WaterQualityReport {
    /**
     * a subclass enum for quality type
     */
    public enum Condition {
        Safe,
        Treatable,
        Unsafe
    }

    private Date dateReported;
    private int id;
    private String workerName;
    private Location location;
    private Condition condition;
    private int virus;
    private int contaminant;

    /**
     * report constructor
     * @param id report id
     * @param dateReported report date
     * @param location water location
     * @param workerName report submitters name
     * @param condition water condition
     * @param virus ppm of viruses
     * @param contaminant ppm of contaminants
     */
    public WaterQualityReport(int id, Date dateReported, Location location,
                              String workerName, Condition condition,
                              int virus, int contaminant) {
        this.dateReported = dateReported;
        this.id = id;
        this.workerName = workerName;
        this.location = location;
        this.condition = condition;
        this.virus = virus;
        this.contaminant = contaminant;
    }

    /**
     * gets the date reported
     * @return date report created
     */
    public Date getDateReported() {
        return dateReported;
    }

    /**
     * gets report id
     * @return report id
     */
    public int getId() {
        return id;
    }

    /**
     * sets report id
     * @param id the id to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets name of submitter
     * @return worker's name
     */
    public String getWorkerName() {
        return workerName;
    }

    /**
     * sets the worker name
     * @param name name of worker to be set
     */
    public void setWorkerName(String name) {
        this.workerName = name;
    }

    /**
     * gets the location of water from report
     * @return the water location
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * gets the water condition
     * @return water condition
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * gets ppm of virus
     * @return ppm of virus
     */
    public int getVirus() {
        return virus;
    }

    /**
     * gets ppm of contaminant
     * @return ppm of contaminant
     */
    public int getContaminant() {
        return contaminant;
    }

}
