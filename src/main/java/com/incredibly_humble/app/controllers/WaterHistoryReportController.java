package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.models.WaterSourceReport;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the water history report screen
 */
public class WaterHistoryReportController {
    @Inject
    private ScreenSwitch screenSwitch;
    @Inject
    private Database db;
    @FXML
    private TableColumn<WaterSourceReport, String> reportNumCol;
    @FXML
    private TableColumn<WaterSourceReport, String> locationCol;
    @FXML
    private TableView<WaterSourceReport> waterReportTable;
    @FXML
    private Label dateLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label reportedByLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label typeLabel;

    /**
     * initilaizes the map
     */
    @FXML
    public void initialize() {
        reportNumCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        locationCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getLocation().toString()));
        waterReportTable.setRowFactory(tableView -> {
            TableRow<WaterSourceReport> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                this.setWaterReportView(row.getItem());
            });
            return row;
        });
        waterReportTable.getItems().setAll(db.getWaterSourceReports());
    }

    private void setWaterReportView(WaterSourceReport report) {
        dateLabel.setText(report.getDateReported().toString());
        idLabel.setText(String.valueOf(report.getId()));
        reportedByLabel.setText(report.getWorkerName());
        locationLabel.setText(report.getLocation().toString());
        typeLabel.setText(report.getType().toString());
    }

    /**
     * goes back to previous screen
     * @param event attached to back button
     * @throws IOException if anything fails
     */
    @FXML
    public void backPressed(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage,
                db.getCurrentUser() == null ? ScreenSwitch.WELCOME_SCREEN : ScreenSwitch.HOME_SCREEN);
    }


}
