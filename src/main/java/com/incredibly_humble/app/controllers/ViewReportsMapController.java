package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import com.incredibly_humble.models.WaterQualityReport;
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

abstract public class ViewReportsMapController<T> implements Initializable, MapComponentInitializedListener {


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

    private int onReport = 0;
    private ArrayList<T> reports;
    private GoogleMap map;
    private ArrayList<Marker> markers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        markers = new ArrayList<>();
        mapView.addMapInializedListener(this);
    }

    abstract public String getTitle(T report);

    abstract public LatLong getLatLong(T report);

    abstract public String getDescription(T report);

    abstract public void dbDelete(T report);

    abstract ArrayList<T> dbGetReports();

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
        reports = this.dbGetReports();
        for (int i = 0; i < reports.size(); i++) {
            T r = reports.get(i);
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = this.getLatLong(r);
            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title(this.getTitle(r));

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        String description = this.getDescription(r);
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

    abstract public void enableButtons();

    private void moveToReport(int index) {
        if (reports.size() > index && reports.size() > 0) {
            moveToReport(reports.get(index), false);
        }
    }

    private void moveToReport(T r, boolean reindex) {
        LatLong loc = this.getLatLong(r);
        reportLabel.setText("Report " + this.getTitle(r));
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
    private void onDelete(ActionEvent event) throws IOException {
        if (this.reports.size() > 0) {
            dbDelete(this.reports.remove(this.onReport));
            map.removeMarker(markers.get(this.onReport));
            int currentZoom = map.getZoom();
            map.setZoom(currentZoom - 1);
            map.setZoom(currentZoom);

            this.onReport--;
            this.onNext(event);
        }
    }

}
