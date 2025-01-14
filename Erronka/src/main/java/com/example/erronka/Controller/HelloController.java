package com.example.erronka.Controller;

import com.example.erronka.DB.UsuarioDB;
import com.example.erronka.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController implements StageAwareController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;

    private UsuarioDB usuarioDB;
    private Stage usingStage;

    @Override
    public void setStage(Stage stage) {
        this.usingStage = stage;
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        if (usuarioDB == null) {
            usuarioDB = new UsuarioDB();  // Inicializamos UsuarioDB al hacer login
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        Usuario usuario = new Usuario(username, password);

        if (usuarioDB.verificarLogin(usuario)) {
            SceneChanger.cambiarVentana(usingStage, "main-menu.fxml", "Main Menu");
        } else {
            statusLabel.setText("Credenciales inválidas. Inténtelo de nuevo.");
        }
    }
}
