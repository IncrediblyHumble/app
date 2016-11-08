package com.incredibly_humble.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * a class representing the source report database
 */
public class WaterSourceReports implements Serializable {
    private ArrayList<WaterSourceReport> reports;

    /**
     * a constructor for the database
     * @param reports the list of reports to be inputted
     */
    public WaterSourceReports(ArrayList<WaterSourceReport> reports){
        this.reports = reports;
    }

    /**
     * gets the list of reports
     * @return a list of reports
     */
    public ArrayList<WaterSourceReport> getReports(){
        return reports;
    }
}
