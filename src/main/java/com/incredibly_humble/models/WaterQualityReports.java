package com.incredibly_humble.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * a class representing the database of quality reports
 */
public class WaterQualityReports implements Serializable {
    private ArrayList<WaterQualityReport> reports;

    /**
     * constructs database
     * @param reports the list of reports to be added
     */
    public WaterQualityReports(ArrayList<WaterQualityReport> reports){
        this.reports = reports;
    }

    /**
     * gets the reports from the database
     * @return the list of  quality reports
     */
    public ArrayList<WaterQualityReport> getReports(){
        return reports;
    }
}
