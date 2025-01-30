package com.example.erronka.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ChatController {
    @FXML
    private TextArea chatDisplay;
    @FXML
    private TextField messageInput;

    private PrintWriter out; // Para enviar mensajes al servidor
    private String username; // Nombre de usuario

    public void initialize() {
        // Solicitar nombre de usuario
        username = requestUsername();

        // Hilo para conectarse al servidor y recibir mensajes
        new Thread(() -> {
            try (Socket socket = new Socket("192.168.115.154", 12345);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                out = new PrintWriter(socket.getOutputStream(), true);

                // Enviar el nombre de usuario al servidor
                out.println(username);

                String serverMessage;
                // Escuchar mensajes del servidor y mostrarlos en el chat
                while ((serverMessage = in.readLine()) != null) {
                    String finalMessage = serverMessage;
                    Platform.runLater(() -> chatDisplay.appendText(finalMessage + "\n"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    public void onSendMessageClick(ActionEvent event) {
        String message = messageInput.getText().trim(); // Obtiene el mensaje del campo de entrada
        if (!message.isEmpty() && out != null) {
            String formattedMessage = "[" + username + "]: " + message; // Incluye el nombre de usuario
            chatDisplay.appendText(formattedMessage + "\n"); // Muestra el mensaje en el chat
            out.println(message); // Envía el mensaje al servidor
            messageInput.clear(); // Limpia el campo de entrada
        }
    }

    private String requestUsername() {
        // Crear un cuadro de diálogo para ingresar el nombre de usuario
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nombre de Usuario");
        dialog.setHeaderText("Bienvenido al Chat");
        dialog.setContentText("Por favor, ingresa tu nombre:");

        // Mostrar el cuadro de diálogo y capturar el nombre ingresado
        Optional<String> result = dialog.showAndWait();
        return result.orElse("Usuario" + (int) (Math.random() * 1000)); // Si no se ingresa nada, usar un nombre predeterminado
    }

}
