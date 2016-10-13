package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.models.WaterReport;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

/**
 * Controller for the water source screen
 */
public class WaterSourceController {
    @Inject
    private ScreenSwitch screenSwitch;
    @FXML
    private TextField locationField;
    @FXML
    private ComboBox typeBox;
    @FXML
    private ComboBox conditionBox;
    @Inject
    Database db;

    /**
     * Sets the account types in the combo box
     */
    @FXML
    public void initialize() {
        typeBox.getItems().setAll(WaterReport.WaterType.values());
        conditionBox.getItems().setAll(WaterReport.WaterCondition.values());
    }

    @FXML
    private void onSubmit(ActionEvent event) throws IOException {
        db.addWaterReport(new WaterReport(0,
                new Date(),
                locationField.getText(), db.getCurrentUser().getName(),
                (WaterReport.WaterType) typeBox.getSelectionModel().getSelectedItem(),
                (WaterReport.WaterCondition) conditionBox.getSelectionModel().getSelectedItem()
        ));
        screenSwitch.toScreen((Stage) ((Node) event.getSource()).getScene().getWindow(),
                ScreenSwitch.HOME_SCREEN);
    }

    @FXML
    private void onCancel(ActionEvent event) throws IOException {
        screenSwitch.toScreen((Stage) ((Node) event.getSource()).getScene().getWindow(),
                ScreenSwitch.HOME_SCREEN);
    }
}

