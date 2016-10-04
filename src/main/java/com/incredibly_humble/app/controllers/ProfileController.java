package com.incredibly_humble.app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.inject.Inject;
import com.incredibly_humble.app.util.Login;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProfileController {

    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextArea address;
    @FXML
    private CheckBox subscribed;
    @FXML
    private Button save;
    @FXML
    private Label success;
    @Inject
    private Login user;
    @Inject
    private ScreenSwitch screenSwitch;



    public void saveProfile(ActionEvent event) {
        // TO IMPLEMENT: Saves current info put into profile Screen for the current user
        // Check out serverUtil User for information on the text fields
        // look at profileScreen on SceneBuilder for an idea

    }

    public void cancelProfile(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, "/views/home.fxml");
    }
}
