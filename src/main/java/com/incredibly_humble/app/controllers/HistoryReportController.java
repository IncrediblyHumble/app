package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import com.incredibly_humble.models.Location;
import com.incredibly_humble.models.User;
import com.incredibly_humble.models.WaterQualityReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Controller for the home screen
 */
public class HistoryReportController {
    @Inject
    private ScreenSwitch screenSwitch;

    @Inject
    private Database db;
    HashMap<String, ArrayList<WaterQualityReport>> reports;
    @FXML
    ComboBox yComboBox;
    @FXML
    ComboBox locComboBox;
    @FXML
    ComboBox yearComboBox;
    @FXML
    ScatterChart<Number, Number> historyChart;

    int year;
    String y;
    String loc;

    @FXML
    private void initialize() throws IOException {
        this.reports = new HashMap<>();
        ArrayList<WaterQualityReport> reps = db.getWaterQualityReports();
        for (WaterQualityReport rep : reps) {
            ArrayList<WaterQualityReport> r;
            if (this.reports.containsKey(rep.getLocation().toString())) {
                r = this.reports.get(rep.getLocation().toString());
                r.add(rep);
                this.reports.put(rep.getLocation().toString(), r);
            } else {
                r = new ArrayList<>();
                r.add(rep);
                this.reports.put(rep.getLocation().toString(), r);
            }
        }
        this.locComboBox.getItems().setAll(this.reports.keySet());
        this.locComboBox.getSelectionModel().select(0);

        String[] values = {"Virus", "Contaminant"};
        this.yComboBox.getItems().setAll(values);
        this.yComboBox.getSelectionModel().select(0);
        y = values[0];
        loc = (String) this.locComboBox.getSelectionModel().getSelectedItem();
        this.setYears();
    }

    /**
     * changes the location
     * @param event attached to location button
     * @throws IOException if any issues
     */
    public void locChanged(ActionEvent event) throws IOException {
        loc = (String) this.locComboBox.getSelectionModel().getSelectedItem();
        setYears();
    }

    /**
     * changes the y axis of report
     * @param event the current event
     * @throws IOException if any issues
     */
    public void yChanged(ActionEvent event) throws IOException {
        y = (String) this.yComboBox.getSelectionModel().getSelectedItem();
        chartChanged();
    }

    /**
     * changes the year of the report
     * @param event attached to year button
     * @throws IOException if any issues
     */
    public void yearChanged(ActionEvent event) throws IOException {
        year = (Integer) this.yearComboBox.getSelectionModel().getSelectedItem();
        chartChanged();
    }


    private void setYears() throws IOException {
        ArrayList<WaterQualityReport> reps = this.reports.get(loc);
        Set<Integer> years = new HashSet<>();
        Calendar cal = Calendar.getInstance();
        for (WaterQualityReport rep : reps) {
            cal.setTime(rep.getDateReported());
            int YEAR = cal.get(Calendar.YEAR);
            years.add(YEAR);
        }
        this.yearComboBox.getItems().setAll(years.toArray());
        this.yearComboBox.getSelectionModel().select(0);
        yearChanged(null);
    }

    /**
     * occurs when back button is pressed
     * @param event attached to back button
     * @throws IOException if any issues
     */
    @FXML
    public void onBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, ScreenSwitch.HOME_SCREEN);
    }

    /**
     * changes the chart to be displayed
     */
    @FXML
    public void chartChanged() {
        XYChart.Series data = new XYChart.Series();
        String yAxis = (String) yComboBox.getSelectionModel().getSelectedItem();
        ArrayList<WaterQualityReport> reps = this.reports.get(loc);
        double[] value = new double[12];
        double[] counter = new double[12];
        Calendar cal = Calendar.getInstance();
        for (WaterQualityReport rep : reps) {
            cal.setTime(rep.getDateReported());
            if (cal.get(Calendar.YEAR) ==this.year) {
                int month = cal.get(Calendar.MONTH);
                int ppm = yAxis == "Virus" ? rep.getVirus() : rep.getContaminant();
                value[month] += ppm;
                counter[month]++;
            }
        }
        for (int i = 0; i < 12; i++) {
            if (counter[i] != 0) {
                data.getData().add(new XYChart.Data(i + 1, value[i] / counter[i]));
            }
        }
        historyChart.getData().clear();
        historyChart.getData().addAll(data);

    }
}
