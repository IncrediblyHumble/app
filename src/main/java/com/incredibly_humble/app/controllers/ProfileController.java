package com.incredibly_humble.app.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class ProfileController {

    @FXML
    private TextField user;
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

    public void saveProfile(ActionEvent event) {
        // TO IMPLEMENT: Saves current info put into profile Screen for the current user
        // Check out serverUtil User for information on the text fields
        // look at profileScreen on SceneBuilder for an idea

    }

    public void cancelProfile(ActionEvent event) {
        // TO IMPLEMENT
        // Cancel out of profile page, erases what was in field before, go back to home screen
    }
}
