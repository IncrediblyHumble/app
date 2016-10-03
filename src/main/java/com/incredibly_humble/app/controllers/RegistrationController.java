package com.incredibly_humble.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import serverUtil.Login;
import serverUtil.impl.LoginHardcoded;
import serverUtil.impl.TriesExceededException;
import java.io.IOException;

public class RegistrationController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField passField;



    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancelPressed(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("loginScreen/loginScreen.fxml"));
        primaryStage.setScene(new Scene(root, 700, 500));
    }
    //TO IMPLEMENT: THE COMBO BOX WITH OPTIONS FOR USER TYPE
    // ObservableList<String> options =
    //         FXCollections.observableArrayList(
    //                 "ADMIN",
    //                 "USER",
    //                 "MANAGER",
    //                 "WORKER"
    //         );
    // final ComboBox typeComboBox = new ComboBox(options);
    // @FXML
    // private void setTypeComboBox(){
    //     typeComboBox.getSelectionModel().select("User");
    // }


    //BASED ON WHICH COMBO BOX WAS MADE, CREATE TYPE OF CLASS AS USER
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOKPressed(ActionEvent event) throws IOException {
        //First validate the data to insure it is at least reasonable
        if (isInputValid()) {
            Login newUser = new LoginHardcoded();
            boolean success = false;
            boolean error = false;
            try {
                success = newUser.verify(nameField.getText(), passField.getText());
            } catch (TriesExceededException e) {
                error = true;
            }
            if (success) {
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("homeScreen/home.fxml"));
                primaryStage.setScene(new Scene(root, 700, 500));
            } else if (error) {
                Alert triesExceededAlert = new Alert(Alert.AlertType.ERROR);
                triesExceededAlert.setHeaderText("Invalid Attempt");
                triesExceededAlert.setContentText("Too many attempted logins");
                triesExceededAlert.showAndWait();
            } else {
                Alert loginAlert = new Alert(Alert.AlertType.ERROR);
                loginAlert.setHeaderText("Please correct invalid fields");
                loginAlert.setContentText("Invalid username or password");
                loginAlert.showAndWait();
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
