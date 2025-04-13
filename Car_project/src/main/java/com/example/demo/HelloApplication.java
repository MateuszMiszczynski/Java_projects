package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1400, 1010);
        stage.setTitle("Symulator");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {Platform.exit();System.exit(0);});
    }

    public static void main(String[] args) {
        launch();


    }
}