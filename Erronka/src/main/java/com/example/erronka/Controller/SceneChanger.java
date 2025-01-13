package com.example.erronka.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {
    /**
     * Cambia la escena del escenario proporcionado usando el archivo FXML especificado.
     * @param stage Escenario actual que no se cierra.
     * @param fxmlFile Nombre del archivo FXML para cargar.
     * @param title Título para la nueva ventana.
     */
    public static void cambiarVentana(Stage stage, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneChanger.class.getResource("/com/example/erronka/" + fxmlFile));
            Scene nextScene = new Scene(loader.load());


            Object controller = loader.getController();
            if (controller instanceof StageAwareController) {
                ((StageAwareController) controller).setStage(stage);
            }

            stage.setScene(nextScene);
            stage.setTitle(title);
            stage.centerOnScreen();
            stage.setMaximized(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
