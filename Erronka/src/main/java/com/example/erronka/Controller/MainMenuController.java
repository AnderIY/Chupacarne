package com.example.erronka.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;


public class MainMenuController implements StageAwareController {

   private Stage stage;

   @Override
   public void setStage(Stage stage) {
      this.stage = stage;
   }

   @FXML
   protected void onErreserbaButtonClick(ActionEvent event) {
      System.out.println("onErreserbaButtonClick called.");

      if (stage == null) {
         System.out.println("Stage is null in MainMenuController.");
         return;
      }

      SceneChanger.cambiarVentana(stage, "Erreserbak.fxml", "Reservation Menu");
   }

   @FXML
   protected void onAlmazenaButtonClick(ActionEvent event) {
      System.out.println("onAlmazenaButtonClick called.");

      if (stage == null) {
         System.out.println("Stage is null in MainMenuController.");
         return;
      }

      SceneChanger.cambiarVentana(stage, "almacen.fxml", "Product Menu");
   }

   @FXML
   protected void onHornitzaileaButtonClick(ActionEvent event) {
      System.out.println("onHornitzaileaButtonClick called.");

      if (stage == null) {
         System.out.println("Stage is null in MainMenuController.");
         return;
      }

      SceneChanger.cambiarVentana(stage, "Proveedor.fxml", "Supplier Menu");
   }
   @FXML
   protected void onLangileaButtonClick(ActionEvent event) {
      System.out.println("onLangileaButtonClick called.");

      if (stage == null) {
         System.out.println("Stage is null in MainMenuController.");
         return;
      }

      SceneChanger.cambiarVentana(stage, "Langilea.fxml", "Worker Menu");
   }
}
