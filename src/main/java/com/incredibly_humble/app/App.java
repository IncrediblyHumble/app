package com.incredibly_humble.app;

import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.Inject;
import com.incredibly_humble.models.User;
import com.incredibly_humble.app.util.impl.Module;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import com.incredibly_humble.app.util.impl.exceptions.RemoteDatabase;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Arrays;

/**
 * a class to represent the application
 */
public class App extends Application {

    private GuiceContext context = new GuiceContext(this, () -> Arrays.asList(new Module()));
    @Inject
    private ScreenSwitch screenSwitch;

    /**
     * a method to start the app
     * @param primaryStage the inital startup of the view
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        context.init();
        primaryStage.setTitle("Clean Water Crowdsourcing");
        screenSwitch.toScreen(primaryStage, "/views/welcome.fxml");
        primaryStage.show();
    }

    /**
     * launchs the program when run
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
