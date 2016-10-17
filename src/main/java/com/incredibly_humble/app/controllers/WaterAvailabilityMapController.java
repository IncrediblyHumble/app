package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.models.WaterReport;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WaterAvailabilityMapController implements Initializable, MapComponentInitializedListener {

    /**
     * a gui view provided by the GMapFX library
     */
    @FXML
    private GoogleMapView mapView;

    /**
     * the actual javascript interface for the google map itself
     */
    private GoogleMap map;

    @Inject
    private Database db;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
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

        ArrayList<WaterReport> reports = db.getWaterReports();
        for (WaterReport r : reports) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = new LatLong(r.getLoc().getLatitude(), r.getLoc().getLongitude());

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title("WATER REPORT "+ r.getId());

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        String description = String.format(
                                "<p>Condition: %s</p><p>Type: %s</p><p>Reported On: %s</p><p>Reported By: %s</p><p>Location: %s</p>",
                                r.getCondition().toString(),
                                r.getType().toString(), r.getDateReported().toString(),
                                r.getWorkerName(), r.getLoc().toString());
                        infoWindowOptions.content(description);

                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);

                    });

            map.addMarker(marker);
        }
    }

}
