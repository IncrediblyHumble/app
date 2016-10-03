package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.app.models.User;
import com.incredibly_humble.app.util.Login;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField passField;

    @FXML
    private ComboBox typeBox;

    @Inject
    Login user;
    @Inject
    ScreenSwitch screenSwitch;

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancelPressed(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, "/views/login.fxml");
    }
    @FXML private void handleRegistrationPressed(ActionEvent event) throws IOException{

    }

    @FXML
    public void initialize() {
        typeBox.getItems().setAll(User.AccountType.values());
        typeBox.getSelectionModel().select(0);
    }

}
