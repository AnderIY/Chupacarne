package com.example.erronka;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static com.example.erronka.ConnectDB.*;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
   protected void onLoginButtonClick() {
         new ConnectDB.ConnectionDB();

   }
}