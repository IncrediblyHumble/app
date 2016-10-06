package com.incredibly_humble.app.util.impl;

import com.google.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenSwitch {

    public static String HOME_SCREEN = "/views/home.fxml";
    public static String LOGIN_SCREEN= "/views/login.fxml";
    public static String PROFILE_SCREEN = "/views/profile.fxml";
    public static String REGISTRATION_SCREEN = "/views/registration.fxml";
    public static String WATER_SOURCE_SCREEN = "/views/waterSource.fxml";
    public static String WELCOME_SCREEN = "/views/welcome.fxml";
    @Inject
    private FXMLLoader fxmlLoader;
    /**
     * @param primaryStage of the current screen
     * @param path string we want to go to
     * @throws IOException
     * switches to the screen we wish to go to
     */
    public void toScreen(Stage primaryStage, String path) throws IOException{
        fxmlLoader.setLocation(getClass().getResource(path));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root, 700, 500));
    }
}
