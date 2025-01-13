package com.example.erronka;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import javax.swing.*;

public class MainMenuController {
   @FXML
   private JButton menuBar;
   private Stage stage;

   public void setStage(Stage stage) {
      this.stage = stage;
      stage.setMaximized(true);
   }

}
