package com.example.erronka.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javax.swing.*;

import static com.example.erronka.Controller.SceneChanger.cambiarVentana;

public class MainMenuController implements StageAwareController {
   @FXML
   private JButton menuBar;
   private Stage stage;

   public void setStage(Stage stage) {
      this.stage = stage;
      stage.setMaximized(true);
   }
   @FXML
   protected void onErreserbaButtonClick(ActionEvent event) {
      cambiarVentana(stage,"Erreserbak.fxml","Erreserbak");
}}
