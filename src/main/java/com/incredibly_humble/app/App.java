package com.incredibly_humble.app;

import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.Inject;
import com.incredibly_humble.app.util.impl.Module;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;

public class App extends Application {

    private GuiceContext context = new GuiceContext(this, () -> Arrays.asList(new Module()));
    @Inject
    private ScreenSwitch screenSwitch;

    @Override
    public void start(Stage primaryStage) throws Exception{
        context.init();
        primaryStage.setTitle("Hello World");
        screenSwitch.toScreen(primaryStage, "/views/welcome.fxml");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
