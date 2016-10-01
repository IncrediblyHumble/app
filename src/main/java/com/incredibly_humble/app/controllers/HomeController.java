package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @Inject
    private FXMLLoader fxmlLoader;

    public void logout(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        fxmlLoader.setLocation(getClass().getResource("/views/welcome.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root, 700, 500));
    }
}
