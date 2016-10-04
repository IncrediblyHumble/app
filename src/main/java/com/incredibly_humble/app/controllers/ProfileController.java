package com.incredibly_humble.app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.inject.Inject;
import com.incredibly_humble.app.models.User;
import com.incredibly_humble.app.util.Database;
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
    @Inject
    private ScreenSwitch screenSwitch;
    @Inject
    Database db;
    private User user;
    @FXML
    public void initialize(){
        user = db.getCurrentUser();
        name.setText(user.getName());
        phone.setText(user.getPhone());
        email.setText(user.getEmail());
        address.setText(user.getAddress());
    }

    public void saveProfile(ActionEvent event) throws Exception {
        user.setAddress(address.getText());
        user.setEmail(email.getText());
        user.setPhone(phone.getText());
        db.updateUser(user);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, "/views/home.fxml");

    }

    public void cancelProfile(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, "/views/home.fxml");
    }
}
