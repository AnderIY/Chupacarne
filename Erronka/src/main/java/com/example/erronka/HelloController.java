package com.example.erronka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.Connection;

public class HelloController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;

    private UsuarioDB usuarioDB;
    private Stage usingStage;

    @FXML
    public void initialize() {
        Connection connection = ConnectDB.getConnection();
        usuarioDB = new UsuarioDB(connection);
    }

    public void setUsingStage(Stage stage) {
        this.usingStage = stage;
    }

    private Stage getUsingStage() {
        return usingStage;
    }

    private void cambiarVentana(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/erronka/" + fxmlFile));
            Scene nextScene = new Scene(loader.load());
            Stage currentStage = getUsingStage();

            MainMenuController controller = loader.getController();
            controller.setStage(currentStage);

            currentStage.setScene(nextScene);
            currentStage.setTitle(title);
            currentStage.centerOnScreen();
            currentStage.setMaximized(true);
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Error al cargar la página.");
        }
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Usuario usuario = new Usuario(username, password);

        if (usuarioDB.verificarLogin(usuario)) {
            cambiarVentana("main-menu.fxml", "Main Menu");
        } else {
            statusLabel.setText("Credenciales inválidas. Inténtelo de nuevo.");
        }
    }
}
