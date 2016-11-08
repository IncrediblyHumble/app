package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import com.incredibly_humble.models.WaterSourceReport;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WaterAvailabilityMapController extends ViewReportsMapController<WaterSourceReport>
        implements Initializable, MapComponentInitializedListener {

    @FXML
    Button deleteButton;
    @FXML
    Button nextButton;
    @FXML
    Button prevButton;

    @Inject
    private ScreenSwitch screenSwitch;
    @Inject
    private Database db;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
    }

    public ArrayList<WaterSourceReport> dbGetReports(){
        return db.getWaterSourceReports();
    }

    public String getTitle(WaterSourceReport r) {
        return "" + r.getId();
    }

    public LatLong getLatLong(WaterSourceReport r) {
        return new LatLong(r.getLocation().getLatitude(), r.getLocation().getLongitude());
    }

    public String getDescription(WaterSourceReport r) {
        return String.format(
                "<p>Type: %s</p><p>Reported On: %s</p><p>Reported By: %s</p><p>Location: %s</p>",
                r.getType().toString(), r.getDateReported().toString(), r.getWorkerName(), r.getLocation().toString());
    }

    public void dbDelete(WaterSourceReport r){
        db.deleteWaterSourceReport(r);
    }

    public void enableButtons() {
        nextButton.setDisable(false);
        prevButton.setDisable(false);
        if (db.getCurrentUser() != null)
            deleteButton.setDisable(false);
    }

    @FXML
    private void onBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage,
                db.getCurrentUser() == null ? ScreenSwitch.WELCOME_SCREEN : ScreenSwitch.HOME_SCREEN);
    }
}