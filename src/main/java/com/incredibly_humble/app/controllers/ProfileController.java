package com.incredibly_humble.app.controllers;

import java.io.IOException;

import com.google.inject.Inject;
import com.incredibly_humble.models.User;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controller for the profile screen
 */
public class ProfileController {

    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField address;
    @Inject
    private ScreenSwitch screenSwitch;
    @Inject
    Database db;
    private User user;

    /**
     * Method for initializing the text boxes
     */
    @FXML
    public void initialize(){
        user = db.getCurrentUser();
        name.setText(user.getName());
        phone.setText(user.getPhone());
        emailLabel.setText(user.getEmail());
        address.setText(user.getAddress());
    }

    /**
     * Event handler if the user presses the save button
     *
     * @param event ActionEvent connected to the save button
     * @throws IOException in case of error in the ActionEvent
     */
    public void saveProfile(ActionEvent event) throws Exception {
        user.setAddress(address.getText());
        user.setPhone(phone.getText());
        db.updateUser(user);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, ScreenSwitch.HOME_SCREEN);

    }

    /**
     * Event handler if the user presses the cancel button
     *
     * @param event ActionEvent connected to the cancel button
     * @throws IOException in case of error in the ActionEvent
     */
    public void cancelProfile(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        screenSwitch.toScreen(primaryStage, ScreenSwitch.HOME_SCREEN);
    }
}
