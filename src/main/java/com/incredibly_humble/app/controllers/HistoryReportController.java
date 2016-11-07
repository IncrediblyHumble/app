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
    ScatterChart<Number, Number> historyChart;

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
        chartChanged(null);
    }

    @FXML
    public void onBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, ScreenSwitch.VIEW_QUALITY_REPORTS_SCREEN);
    }

    @FXML
    public void chartChanged(ActionEvent event) throws IOException {
        XYChart.Series data = new XYChart.Series();
        String date = (String) locComboBox.getSelectionModel().getSelectedItem();
        String yAxis = (String) yComboBox.getSelectionModel().getSelectedItem();
        ArrayList<WaterQualityReport> reps = this.reports.get(date);

        double [] value = new double[12];
        double[] counter = new double[12];
        for (WaterQualityReport rep : reps) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(rep.getDateReported());
            int month = cal.get(Calendar.MONTH);
            int ppm = yAxis == "Virus" ? rep.getVirus() : rep.getContaminant();
            value[month] += ppm;
            counter[month]++;
        }
        for(int i = 0; i < 12; i++){
            if (counter[i] != 0) {
                data.getData().add(new XYChart.Data(i+1, value[i]/counter[i]));
                System.out.println(value[i]/counter[i]);
            }
        }
        historyChart.getData().clear();
        historyChart.getData().addAll(data);

    }
}
