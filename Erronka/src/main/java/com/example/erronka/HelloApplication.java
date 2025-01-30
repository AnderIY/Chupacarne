package com.example.erronka;

import com.example.erronka.Controller.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setTitle("Chupacarne");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    
    }

    public static void main(String[] args) {
        launch();
    }
}