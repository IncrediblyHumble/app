package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import com.incredibly_humble.app.util.Login;
import com.incredibly_humble.app.util.impl.exceptions.TriesExceededException;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;

/**
 * Controller for the login screen
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
     *
     * @param event ActionEvent connected to the cancel button
     * @throws IOException in case of error in the ActionEvent
     */
    @FXML
    private void handleCancelPressed(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, ScreenSwitch.WELCOME_SCREEN);
    }

    /**
     * Event Handler for the ok button
     *
     * @param event ActionEvent connected to the ok button
     * @throws IOException in case of error in the ActionEvent
     */
    @FXML
    private void handleOKPressed(ActionEvent event) throws IOException {
        //First validate the data to insure it is at least reasonable
        if (nameField.getText() != null && nameField.getText().length() != 0 &&
                passField.getText() != null && passField.getText().length() != 0) {
            try {
                if (user.verify(nameField.getText(), passField.getText())) {
                    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    screenSwitch.toScreen(primaryStage, ScreenSwitch.HOME_SCREEN);
                }
            } catch (DatabaseException e) {
                alert("Error", e.getMessage(), "Please correct");

            }
        } else {
            alert("Input is invalid", "Invalid Fields", "Please correct invalid fields");
        }
    }

    /**
     * Event Handler for the register button
     *
     * @param event ActionEvent connected to the register button
     * @throws IOException in case of error in the ActionEvent
     */
    @FXML
    private void handleRegisterPressed(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, ScreenSwitch.REGISTRATION_SCREEN);
    }

    /**
     * @param context String that says what kind of error is happening
     * @param header  String description of how to fix the error
     * @param title   String that gives the title of the error
     */
    private void alert(String context, String header, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR, context);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
