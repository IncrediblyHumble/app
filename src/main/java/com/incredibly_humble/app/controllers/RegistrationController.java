package com.incredibly_humble.app.controllers;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.Login;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import com.incredibly_humble.app.util.impl.TriesExceededException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField passField;

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


    private void alert(String context, String header, String title){
        Alert alert = new Alert(Alert.AlertType.ERROR, context);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
