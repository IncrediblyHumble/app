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

public class HomeController {
    @Inject private ScreenSwitch screenSwitch;

    public void logout(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, "/views/welcome.fxml");
    }
}
