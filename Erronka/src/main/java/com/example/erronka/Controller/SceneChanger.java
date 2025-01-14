package com.example.erronka.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {

    public static void cambiarVentana(Stage stage, String fxmlFileName, String title) {
        if (stage == null) {
            System.out.println("Stage is null. Cannot change window.");
            return;
        }

        boolean wasMaximized = stage.isMaximized();

        try {

            FXMLLoader loader = new FXMLLoader(SceneChanger.class.getResource("/com/example/erronka/" + fxmlFileName));
            Scene scene = new Scene(loader.load());

            if (loader.getController() instanceof StageAwareController) {
                ((StageAwareController) loader.getController()).setStage(stage);
            }

            stage.setMaximized(true);

            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();

            if (wasMaximized) {
                stage.setMaximized(true);
            } else {
                stage.setMaximized(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
