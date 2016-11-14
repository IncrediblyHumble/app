package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.models.Location;
import com.incredibly_humble.models.WaterSourceReport;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.util.Date;

/**
 * Controller for the water source screen
 */
public class CreateWaterSourceReportController extends CreateReportWithMap implements MapComponentInitializedListener {
    @FXML
    private TextField latField;
    @FXML
    private TextField longField;
    private GoogleMap map;

    @Inject
    private ScreenSwitch screenSwitch;
    @FXML
    private ComboBox typeBox;
    @Inject
    Database db;
    /**
     * Sets the account types in the combo box
     */
    @FXML
    public void initialize() {
        super.initialize();
        typeBox.getItems().setAll(WaterSourceReport.WaterType.values());
        typeBox.getSelectionModel().select(0);
    }

    /**
     * initializes the map
     */
    @Override
    public void mapInitialized() {
        super.mapInitialized();
    }

    @FXML
    private void onSubmit(ActionEvent event) throws IOException {
        db.addWaterSourceReport(new WaterSourceReport(0,
                new Date(),
                new Location(Double.valueOf(latField.getText()), Double.valueOf(longField.getText())),
                db.getCurrentUser().getName(),
                (WaterSourceReport.WaterType) typeBox.getSelectionModel().getSelectedItem()
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

