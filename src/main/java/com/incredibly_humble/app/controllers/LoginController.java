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

    @Inject private Login newUser;
    @Inject private ScreenSwitch screenSwitch;

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancelPressed(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, "/views/welcome.fxml");
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOKPressed(ActionEvent event) throws IOException {
        //First validate the data to insure it is at least reasonable
        if (isInputValid()) {
            try {
                if(newUser.verify(nameField.getText(), passField.getText())) {
                    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    screenSwitch.toScreen(primaryStage, "/views/home.fxml");
                }  else {
                    Alert loginAlert = new Alert(Alert.AlertType.ERROR);
                    loginAlert.setHeaderText("Please correct invalid fields");
                    loginAlert.setContentText("Invalid username or password");
                    loginAlert.showAndWait();
                }
            } catch (TriesExceededException e) {
                Alert triesExceededAlert = new Alert(Alert.AlertType.ERROR);
                triesExceededAlert.setHeaderText("Invalid Attempt");
                triesExceededAlert.setContentText("Too many attempted logins");
                triesExceededAlert.showAndWait();
            }
        }
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        boolean error = false;

        //checks if they typed something
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            error = true;
            errorMessage += "No valid username entered.\n";
        }
        if (passField.getText() == null || passField.getText().length() == 0) {
            error = true;
            errorMessage += "No valid password entered.\n";
        }

        //no error message means success / good input
        if (!error) {
            return true;
        } else {
            // Puts an alert if they didn't do anything
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
