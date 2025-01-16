package com.example.erronka.Controller;

import com.example.erronka.DB.ProveedorDB;
import com.example.erronka.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProveedorController {

    @FXML
    private TextField cifField;

    @FXML
    private TextField direccionField;

    @FXML
    private TextField kontuKorronteaField;

    @FXML
    private TableView<Proveedor> proveedorTable;

    @FXML
    private TableColumn<Proveedor, Number> idColumn;

    @FXML
    private TableColumn<Proveedor, String> cifColumn;

    @FXML
    private TableColumn<Proveedor, String> direccionColumn;

    @FXML
    private TableColumn<Proveedor, String> kontuKorronteaColumn;

    @FXML
    private TableColumn<Proveedor, Boolean> activoColumn;

    private final ObservableList<Proveedor> proveedorList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        cifColumn.setCellValueFactory(cellData -> cellData.getValue().cifProperty());
        direccionColumn.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        kontuKorronteaColumn.setCellValueFactory(cellData -> cellData.getValue().kontuKorronteaProperty());

        proveedorTable.setItems(proveedorList);
        loadProveedores();
    }

    @FXML
    protected void onGuardarButtonClick(ActionEvent event) {
        String cif = cifField.getText();
        String direccion = direccionField.getText();
        String kontuKorrontea = kontuKorronteaField.getText();

        Proveedor proveedor = new Proveedor(cif, direccion, kontuKorrontea);
        ProveedorDB.insertProveedor(proveedor);
        proveedorList.add(proveedor);
        clearFields();
    }

    @FXML
    protected void onEliminarButtonClick(ActionEvent event) {
        Proveedor selected = proveedorTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ProveedorDB.deleteProveedor(selected.getId());
            proveedorList.remove(selected);
        }
    }
    @FXML
    protected void handleDeleteProveedor(ActionEvent event) {
        Proveedor selected = proveedorTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "¿Seguro que desea eliminar este proveedor?", ButtonType.YES, ButtonType.NO);
            confirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    ProveedorDB.deleteProveedor(selected.getId());
                    proveedorList.remove(selected);
                }
            });
        }
    }

    @FXML
    protected void handleUpdateProveedor(ActionEvent event) {
        Proveedor selected = proveedorTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setCif(cifField.getText());
            selected.setDireccion(direccionField.getText());
            selected.setKontuKorrontea(kontuKorronteaField.getText());
            ProveedorDB.updateProveedor(selected);
            proveedorTable.refresh();
        }
    }
    @FXML
    protected void handleRowSelection() {
        Proveedor selected = proveedorTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            cifField.setText(selected.getCif());
            direccionField.setText(selected.getDireccion());
            kontuKorronteaField.setText(selected.getKontuKorrontea());
        }
    }

    private void loadProveedores() {
        proveedorList.setAll(ProveedorDB.getAllProveedores());
    }

    private void clearFields() {
        cifField.clear();
        direccionField.clear();
        kontuKorronteaField.clear();
    }
}
