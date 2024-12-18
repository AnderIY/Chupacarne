package com.example.erronka;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;


    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Por favor, completa todos los campos.");
            System.out.println("Error: Los campos de usuario o contraseña están vacíos.");
            return;
        }

        LoginService loginService = new LoginService();
        try {
            if (!loginService.isConnected()) {
                statusLabel.setText("No se pudo conectar a la base de datos.");
                System.out.println("Error: Conexión a la base de datos fallida.");
                return;
            }

            boolean isAuthenticated = loginService.verifyLogin(username, password);
            if (isAuthenticated) {
                statusLabel.setText("¡Inicio de sesión exitoso!");
                welcomeText.setText("Bienvenido, " + username + "!");
            } else {
                statusLabel.setText("Usuario o contraseña incorrectos.");
            }
        } finally {
            loginService.closeService();
        }
    }
}
