package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import com.incredibly_humble.app.util.Login;
import com.incredibly_humble.app.util.impl.TriesExceededException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Created by Derek Henry
 */
public class LoginController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField passField;

    @Inject
    private Login user;
    @Inject
    private ScreenSwitch screenSwitch;

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancelPressed(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, "/views/welcome.fxml");
    }

    @FXML
    private void handleOKPressed(ActionEvent event) throws IOException {
        //First validate the data to insure it is at least reasonable
        if (nameField.getText() != null && nameField.getText().length() != 0 &&
                passField.getText() != null && passField.getText().length() != 0) {
            try {
                if (user.verify(nameField.getText(), passField.getText())) {
                    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    screenSwitch.toScreen(primaryStage, "/views/home.fxml");
                } else {
                    alert("Invalid username or password", "Username and Password are incorrect", "Invalid username or password");

                }
            } catch (TriesExceededException e) {
                alert("Invalid username or password", "Please correct invalid fields", "Too many attempts");

            }
        } else {
            alert("Input is invalid", "Invalid Fields", "Please correct invalid fields");
        }
    }

    @FXML private void handleRegisterPressed(ActionEvent event)throws IOException{
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, "/views/registration.fxml");
    }
    private void alert(String context, String header, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR, context);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
