package com.example.erronka.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {

    public static void cambiarVentana(Stage stage, String fxmlFileName, String title) {
        if (stage == null) {
            System.out.println("Stage is null. Cannot change window.");
            return;
        }

        try {

            FXMLLoader loader = new FXMLLoader(SceneChanger.class.getResource("/com/example/erronka/" + fxmlFileName));
            Scene scene = new Scene(loader.load());

            if (loader.getController() instanceof StageAwareController) {
                ((StageAwareController) loader.getController()).setStage(stage);
            }
            stage.setWidth(Screen.getPrimary().getBounds().getWidth());
            stage.setHeight(Screen.getPrimary().getBounds().getHeight());
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.setTitle(title);

            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
