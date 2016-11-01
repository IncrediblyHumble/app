package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import com.incredibly_humble.models.Location;
import com.incredibly_humble.models.WaterQualityReport;
import com.incredibly_humble.models.WaterSourceReport;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

/**
 * Controller for the water source screen
 */
public class CreateWaterQualityReportController extends CreateReportWithMap implements MapComponentInitializedListener {
    @FXML
    private TextField latField;
    @FXML
    private TextField longField;
    @FXML
    private TextField virusField;
    @FXML
    private TextField contaminantField;

    @Inject
    private ScreenSwitch screenSwitch;
    @FXML
    private ComboBox conditionBox;
    @Inject
    Database db;

    /**
     * Sets the account types in the combo box
     */
    @FXML
    public void initialize() {
        super.initialize();
        conditionBox.getItems().setAll(WaterQualityReport.Condition.values());
        conditionBox.getSelectionModel().select(0);
    }


    @FXML
    private void onSubmit(ActionEvent event) throws IOException {
        db.addWaterQualityReport(new WaterQualityReport(
                0, new Date(), new Location(Double.valueOf(latField.getText()), Double.valueOf(longField.getText())),
                db.getCurrentUser().getName(),
                (WaterQualityReport.Condition) conditionBox.getSelectionModel().getSelectedItem(),
                Integer.valueOf(virusField.getText()), Integer.valueOf(contaminantField.getText())
        ));
        screenSwitch.toScreen((Stage) ((Node) event.getSource()).getScene().getWindow(),
                ScreenSwitch.HOME_SCREEN);
    }

    @FXML
    private void onCancel(ActionEvent event) throws IOException {
        screenSwitch.toScreen((Stage) ((Node) event.getSource()).getScene().getWindow(),
                ScreenSwitch.HOME_SCREEN);
    }
    @FXML
    private void checkNumeric(KeyEvent keyEvent) throws IOException {
        try{
            Integer.valueOf(keyEvent.getCharacter());
        } catch (Exception e){
            keyEvent.consume();
        }
    }
}

