package com.example.erronka.Controller;

import com.example.erronka.DB.LangileaDB;
import com.example.erronka.Langilea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LangileaController {

    @FXML
    private TextField dniField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField apellido1Field;

    @FXML
    private TextField apellido2Field;

    @FXML
    private TableView<Langilea> langileaTable;

    @FXML
    private TableColumn<Langilea, Number> idColumn;

    @FXML
    private TableColumn<Langilea, String> dniColumn;

    @FXML
    private TableColumn<Langilea, String> nombreColumn;

    @FXML
    private TableColumn<Langilea, String> apellido1Column;

    @FXML
    private TableColumn<Langilea, String> apellido2Column;

    private final ObservableList<Langilea> langileaList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        dniColumn.setCellValueFactory(cellData -> cellData.getValue().dniProperty());
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellido1Column.setCellValueFactory(cellData -> cellData.getValue().apellido1Property());
        apellido2Column.setCellValueFactory(cellData -> cellData.getValue().apellido2Property());

        langileaTable.setItems(langileaList);
        loadLangileak();
    }

    @FXML
    protected void onGuardarButtonClick(ActionEvent event) {
        String dni = dniField.getText();
        String nombre = nombreField.getText();
        String apellido1 = apellido1Field.getText();
        String apellido2 = apellido2Field.getText();

        if (dni.isEmpty() || nombre.isEmpty()) {
            showAlert("Error", "DNI y Nombre son campos obligatorios.");
            return;
        }

        Langilea langilea = new Langilea(dni, nombre, apellido1, apellido2);
        LangileaDB.insertLangilea(langilea);
        langileaList.add(langilea);
        clearFields();
    }

    @FXML
    protected void onEliminarButtonClick(ActionEvent event) {
        Langilea selected = langileaTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            LangileaDB.deleteLangilea(selected.getId());
            langileaList.remove(selected);
        } else {
            showAlert("Error", "Debe seleccionar un trabajador para eliminar.");
        }
    }

    @FXML
    protected void handleRowSelection() {
        Langilea selected = langileaTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            dniField.setText(selected.getDni());
            nombreField.setText(selected.getNombre());
            apellido1Field.setText(selected.getApellido1());
            apellido2Field.setText(selected.getApellido2());
        }
    }

    private void loadLangileak() {
        langileaList.setAll(LangileaDB.getAllLangileak());
    }

    private void clearFields() {
        dniField.clear();
        nombreField.clear();
        apellido1Field.clear();
        apellido2Field.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
