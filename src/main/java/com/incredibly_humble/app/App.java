package com.incredibly_humble.app;

import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.Inject;
import com.incredibly_humble.app.util.impl.Module;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;

/**
 * a class to represent the application
 */
public class App extends Application {

    private GuiceContext context = new GuiceContext(this, () -> Collections.singletonList(new Module()));
    @Inject
    private ScreenSwitch screenSwitch;

    /**
     * a method to start the app
     * @param primaryStage the initial startup of the view
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        context.init();
        primaryStage.setTitle("Clean Water Crowdsourcing");
        screenSwitch.toScreen(primaryStage, ScreenSwitch.WELCOME_SCREEN);
        primaryStage.show();
    }

    /**
     * launches the program when run
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
