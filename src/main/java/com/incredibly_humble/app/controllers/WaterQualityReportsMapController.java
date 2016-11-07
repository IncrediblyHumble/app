package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import com.incredibly_humble.models.WaterQualityReport;
import com.incredibly_humble.models.WaterSourceReport;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.LatLong;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WaterQualityReportsMapController extends ViewReportsMapController<WaterQualityReport>
        implements Initializable, MapComponentInitializedListener {

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

    public ArrayList<WaterQualityReport> dbGetReports() {
        return db.getWaterQualityReports();
    }

    public String getTitle(WaterQualityReport r) {
        return "" + r.getId();
    }

    public LatLong getLatLong(WaterQualityReport r) {
        return new LatLong(r.getLocation().getLatitude(), r.getLocation().getLongitude());
    }

    public String getDescription(WaterQualityReport r) {
        return String.format(
                "<p>Condition: %s</p><p>Reported On: %s</p><p>Reported By: %s</p><p>Location: %s</p><p>VirusPPM: %s</p><p>ContaminantPPM: %s</p>",
                r.getCondition().toString(), r.getDateReported().toString(), r.getWorkerName(), r.getLocation().toString(), r.getVirus(), r.getContaminant());
    }

    public void dbDelete(WaterQualityReport r) {
    }

    public void enableButtons() {
        nextButton.setDisable(false);
        prevButton.setDisable(false);
    }

    @FXML
    private void onBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, ScreenSwitch.HOME_SCREEN);
    }

}
