package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.models.User;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.Login;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;
import com.incredibly_humble.app.util.impl.exceptions.TriesExceededException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the registration screen
 */
public class RegistrationController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField passField;
    @FXML
    private TextField emailField;

    @FXML
    private ComboBox typeBox;

    @Inject
    Login user;
    @Inject
    ScreenSwitch screenSwitch;
    @Inject
    Database db;

    /**
     * Called when the user clicks cancel.
     * @param event ActionEvent connected to the cancel button
     * @throws IOException in case of error in the ActionEvent
     */
    @FXML
    private void handleCancelPressed(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, ScreenSwitch.LOGIN_SCREEN);
    }

    /**
     * Called when the user presses register.
     * @param event ActionEvent connected to the login button
     * @throws IOException in case of error in the ActionEvent
     * @throws TriesExceededException in case the user tries to login in too many times
     */
    @FXML
    private void handleRegistrationPressed(ActionEvent event) throws IOException, TriesExceededException {
        if (nameField.getText() != null && nameField.getText().length() != 0 &&
                passField.getText() != null && passField.getText().length() != 0
                && emailField.getText() != null && emailField.getText().length() != 0) {
            try {
                db.addUser(new User(nameField.getText(), emailField.getText(),
                        passField.getText(),
                        (User.AccountType) typeBox.getSelectionModel().getSelectedItem()));
                user.verify(emailField.getText(), passField.getText());
                screenSwitch.toScreen((Stage) ((Node) event.getSource()).getScene().getWindow(),
                        ScreenSwitch.HOME_SCREEN);
            } catch (DatabaseException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Please Fix");
                alert.setTitle("ERROR");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    /**
     * Sets the account types in the combo box
     */
    @FXML
    public void initialize() {
        typeBox.getItems().setAll(User.AccountType.values());
        typeBox.getSelectionModel().select(0);
    }

}
