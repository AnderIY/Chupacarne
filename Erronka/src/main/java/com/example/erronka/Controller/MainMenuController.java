package com.example.erronka.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController implements StageAwareController {

   private Stage stage;

   @FXML
   private AnchorPane mainMenuPane;

   @Override
   public void setStage(Stage stage) {
      this.stage = stage;
      BaseController.maximizeWindow(stage);
   }
   public void onChatButtonClick(ActionEvent event) {
      try {
         // Cargar el diseño del chat desde el archivo FXML
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/erronka/ChatView.fxml"));
         Parent chatRoot = loader.load();

         // Crear una nueva ventana para el chat
         Stage chatStage = new Stage();
         chatStage.setTitle("Chat");
         chatStage.setScene(new Scene(chatRoot));
         chatStage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   @FXML
   protected void onErreserbaButtonClick(ActionEvent event) {
      SceneChanger.cambiarVentana(stage, "Erreserbak.fxml", "Reservation Menu");
   }

   @FXML
   protected void onAlmazenaButtonClick(ActionEvent event) {
      SceneChanger.cambiarVentana(stage, "almacen.fxml", "Product Menu");
   }

   @FXML
   protected void onHornitzaileaButtonClick(ActionEvent event) {
      SceneChanger.cambiarVentana(stage, "Proveedor.fxml", "Supplier Menu");
   }

   @FXML
   protected void onLangileaButtonClick(ActionEvent event) {
      SceneChanger.cambiarVentana(stage, "Langilea.fxml", "Worker Menu");
   }
}