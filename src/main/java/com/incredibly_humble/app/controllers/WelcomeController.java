package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the welcome screen
 */
public class WelcomeController {
    @Inject private ScreenSwitch screenSwitch;

    /**
     * Event handler for the login button
     *
     * @param event ActionEvent connected to the login button
     * @throws IOException in case of error in the ActionEvent
     */
    public void login(ActionEvent event)throws IOException{
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, ScreenSwitch.LOGIN_SCREEN);
    }


    public void gotoViewReports(ActionEvent event) throws IOException {
        screenSwitch.toScreen((Stage) ((Node) event.getSource()).getScene().getWindow(),
                ScreenSwitch.WATER_REPORT_HISTORY_SCREEN);
    }
}
