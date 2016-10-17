package com.incredibly_humble.app.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.incredibly_humble.models.WaterReport;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventHandler;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Controller for the water source screen
 */
public class WaterSourceController implements  MapComponentInitializedListener {
    @Inject
    private ScreenSwitch screenSwitch;
    @FXML
    private TextField locationField;
    @FXML
    private ComboBox typeBox;
    @FXML
    private ComboBox conditionBox;
    @FXML private TextField latField;
    @FXML private TextField longField;
    @Inject
    Database db;
    @FXML
    private GoogleMapView mapView;
    private GoogleMap map;
    private Marker marker;
    /**
     * Sets the account types in the combo box
     */
    @FXML
    public void initialize() {
        typeBox.getItems().setAll(WaterReport.WaterType.values());
        conditionBox.getItems().setAll(WaterReport.WaterCondition.values());
        mapView.addMapInializedListener(this);
        latField.setText(String.format("%f",34.0));
        longField.setText(String.format("%f",-88));
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

    @FXML
    private void locationChange(ActionEvent event) throws IOException {
        map.removeMarker(marker);
        MarkerOptions markerOptions = new MarkerOptions();
        LatLong loc = new LatLong(Double.valueOf(latField.getText()), Double.valueOf(longField.getText()));

        markerOptions.position(loc)
                .visible(Boolean.TRUE)
                .title("NEW WATER REPORT");

        marker = new Marker(markerOptions);

        map.addMarker(marker);

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
        //TODO add click location functionality
    }
}

