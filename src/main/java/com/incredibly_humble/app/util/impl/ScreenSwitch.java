package com.incredibly_humble.app.util.impl;

import com.google.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenSwitch {

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
