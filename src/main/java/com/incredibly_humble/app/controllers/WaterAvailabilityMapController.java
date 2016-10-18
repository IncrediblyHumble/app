package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import com.incredibly_humble.models.WaterReport;
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

public class WaterAvailabilityMapController implements Initializable, MapComponentInitializedListener {


    @FXML
    private GoogleMapView mapView;
    @FXML
    Label reportLabel;
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

    private int onReport = 0;
    private ArrayList<WaterReport> reports;
    private GoogleMap map;
    private ArrayList<Marker> markers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        markers = new ArrayList<>();
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        options.zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);

        reports = db.getWaterReports();
        for (int i = 0; i < reports.size(); i++) {
            WaterReport r = reports.get(i);
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = new LatLong(r.getLocation().getLatitude(), r.getLocation().getLongitude());

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title("" + r.getId());

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        String description = String.format(
                                "<p>Condition: %s</p><p>Type: %s</p><p>Reported On: %s</p><p>Reported By: %s</p><p>Location: %s</p>",
                                r.getCondition().toString(),
                                r.getType().toString(), r.getDateReported().toString(),
                                r.getWorkerName(), r.getLocation().toString());
                        infoWindowOptions.content(description);

                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);
                        moveToReport(r, true);
                    });

            map.addMarker(marker);
            markers.add(marker);
        }
        moveToReport(0);
        enableButtons();
    }

    private void enableButtons() {
        nextButton.setDisable(false);
        prevButton.setDisable(false);
        if (db.getCurrentUser() != null)
            deleteButton.setDisable(false);
    }

    private void moveToReport(int index) {
        if (reports.size() > index && reports.size() > 0) {
            moveToReport(reports.get(index), false);
        }
    }

    private void moveToReport(WaterReport r, boolean reindex) {
        LatLong loc = new LatLong(r.getLocation().getLatitude(), r.getLocation().getLongitude());
        reportLabel.setText("Report " + r.getId());
        map.setCenter(loc);
        if (reindex) {
            this.onReport = this.reports.indexOf(r);
        }
    }

    @FXML
    private void onNext(ActionEvent event) throws IOException {
        this.onReport++;
        if (this.onReport >= this.reports.size()) {
            this.onReport = 0;
        }
        moveToReport(this.onReport);
    }

    @FXML
    private void onPrev(ActionEvent event) throws IOException {
        this.onReport--;
        if (this.onReport < 0) {
            this.onReport = this.reports.size() - 1;
        }
        moveToReport(this.onReport);
    }

    @FXML
    private void onBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage,
                db.getCurrentUser() == null ? ScreenSwitch.WELCOME_SCREEN : ScreenSwitch.HOME_SCREEN);
    }

    @FXML
    private void onDelete(ActionEvent event) throws IOException {
        if(this.reports.size() >0) {
            db.deleteWaterReport(this.reports.remove(this.onReport));
            map.removeMarker(markers.get(this.onReport));
            int currentZoom = map.getZoom();
            map.setZoom(currentZoom - 1);
            map.setZoom(currentZoom);

            this.onReport--;
            this.onNext(event);
        }
    }

}
