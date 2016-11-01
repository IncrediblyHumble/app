package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import com.incredibly_humble.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the home screen
 */
public class HomeController {
    @Inject
    private ScreenSwitch screenSwitch;

    @Inject
    private Database db;
    @FXML
    Button goToCreateButton;
    @FXML
    Button createQualityReportButton;
    @FXML
    Button goToQualityReportsButton;

    @FXML private void initialize(){
        User.AccountType type = this.db.getCurrentUser().getType();
        this.createQualityReportButton.setVisible(type == User.AccountType.MANAGER || type == User.AccountType.WORKER);
        this.goToQualityReportsButton.setVisible(type == User.AccountType.MANAGER);
    }
    /**
     * Event handler for the logout button
     *
     * @param event ActionEvent connected to the logout button
     * @throws IOException in case of error in the ActionEvent
     */
    public void logout(ActionEvent event) throws IOException {
        db.logout();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, ScreenSwitch.WELCOME_SCREEN);
    }

    /**
     * Event handler for the profile button
     *
     * @param event ActionEvent connected to the profile button
     * @throws IOException in case of error in the ActionEvent
     */
    public void gotoProfile(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, ScreenSwitch.PROFILE_SCREEN);
    }

    public void gotoCreateReport(ActionEvent event) throws IOException {
        screenSwitch.toScreen((Stage) ((Node) event.getSource()).getScene().getWindow(),
                screenSwitch.WATER_SOURCE_SCREEN);
    }

    public void gotoViewReports(ActionEvent event) throws IOException {
        screenSwitch.toScreen((Stage) ((Node) event.getSource()).getScene().getWindow(),
                ScreenSwitch.WATER_AVAILABILITY_MAP_SCREEN);

    }
    public void goToCreateQualityReport(ActionEvent event) throws IOException {
        screenSwitch.toScreen((Stage) ((Node) event.getSource()).getScene().getWindow(),
                screenSwitch.CREATE_WATER_QUALITY_REPORT_SCREEN);
    }
    public void goToQualityReports(ActionEvent event) throws IOException {
        screenSwitch.toScreen((Stage) ((Node) event.getSource()).getScene().getWindow(),
                screenSwitch.VIEW_QUALITY_REPORTS_SCREEN);
    }
}
