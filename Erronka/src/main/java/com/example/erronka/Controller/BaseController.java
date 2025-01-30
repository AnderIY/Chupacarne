package com.example.erronka.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class BaseController {

    public static void navigateToMainMenu(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(BaseController.class.getResource("/com/example/erronka/main-menu.fxml"));
            Parent root = loader.load();

            MainMenuController controller = loader.getController();
            if (controller instanceof StageAwareController) {
                controller.setStage(stage);  // Llama a setStage para maximizar y ajustar la pantalla
            }

            stage.setScene(new Scene(root));
            maximizeWindow(stage);
            adjustToScreenResolution(stage, root);
            stage.setTitle("Main Menu");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void maximizeWindow(Stage stage) {
        if (stage != null) {
            stage.setMaximized(true);  // Maximiza la ventana
        }
    }

    public static void adjustToScreenResolution(Stage stage, Parent root) {
        if (stage == null || root == null) {
            return;
        }

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        double widthRatio = screenWidth / 600.0;
        double heightRatio = screenHeight / 400.0;

        // Ajustar todos los nodos recursivamente
        adjustNodes(root, widthRatio, heightRatio);
    }

    private static void adjustNodes(javafx.scene.Node node, double widthRatio, double heightRatio) {
        if (node instanceof Button button) {
            button.setPrefWidth(button.getPrefWidth() * widthRatio);
            button.setPrefHeight(button.getPrefHeight() * heightRatio);
            button.setLayoutX(button.getLayoutX() * widthRatio);
            button.setLayoutY(button.getLayoutY() * heightRatio);

            String existingStyle = button.getStyle();
            String fontSizeStyle = "-fx-font-size: " + (12 * Math.min(widthRatio, heightRatio)) + "px;";
            button.setStyle(existingStyle + fontSizeStyle);
        } else if (node instanceof TextField textField) {
            textField.setPrefWidth(textField.getPrefWidth() * widthRatio);
            textField.setPrefHeight(textField.getPrefHeight() * heightRatio);
        } else if (node instanceof Label label) {
            label.setStyle("-fx-font-size: " + (14 * Math.min(widthRatio, heightRatio)) + "px;");
        }

        if (node instanceof Parent parent) {
            for (javafx.scene.Node child : parent.getChildrenUnmodifiable()) {
                adjustNodes(child, widthRatio, heightRatio);
            }
        }
    }



}
