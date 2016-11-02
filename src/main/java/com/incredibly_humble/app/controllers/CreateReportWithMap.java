package com.incredibly_humble.app.controllers;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import netscape.javascript.JSObject;

import java.io.IOException;

/**
 * Controller for the water source screen
 */
public abstract class CreateReportWithMap implements MapComponentInitializedListener {
    @FXML
    private TextField latField;
    @FXML
    private TextField longField;
    @FXML
    private GoogleMapView mapView;
    @FXML
    private GoogleMap map;
    private Marker marker;

    /**
     * Sets the account types in the combo box
     */
    @FXML
    public void initialize() {
        mapView.addMapInializedListener(this);
        latField.setText(String.format("%f", 34.0));
        longField.setText(String.format("%f", -88.0));
    }
    @FXML
    private void locationChange(KeyEvent event) throws IOException {
        try {
            moveMarker(Double.valueOf(latField.getText()), Double.valueOf(longField.getText()));
        } catch (RuntimeException e) {
        }
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        //set up the center location for the map
        LatLong center = new LatLong(34, -88);

        options.center(center)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);

        MarkerOptions markerOptions = new MarkerOptions();
        LatLong loc = new LatLong(34, -88);

        markerOptions.position(loc)
                .visible(Boolean.TRUE)
                .title("NEW WATER REPORT");

        marker = new Marker(markerOptions);

        map.addMarker(marker);
        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            moveMarker(ll.getLatitude(), ll.getLongitude());
            latField.setText(String.format("%f", ll.getLatitude()));
            longField.setText(String.format("%f", ll.getLongitude()));

        });
    }

    private void moveMarker(Double lat, Double lon) {
        map.removeMarker(marker);
        MarkerOptions markerOptions = new MarkerOptions();
        LatLong loc = new LatLong(lat, lon);

        markerOptions.position(loc)
                .visible(Boolean.TRUE)
                .title("NEW WATER REPORT");

        marker = new Marker(markerOptions);
        map.addMarker(marker);
        map.setCenter(loc);

        int currentZoom = map.getZoom();
        map.setZoom(currentZoom - 1);
        map.setZoom(currentZoom);
    }
}

