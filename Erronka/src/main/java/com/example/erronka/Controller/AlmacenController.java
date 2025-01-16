package com.example.erronka.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.erronka.DB.ProductoDB;
import com.example.erronka.Producto;

import java.time.LocalDate;

public class AlmacenController {

    @FXML
    private TextField nameField, typeField, unitField, noteField, quantityField, minField, maxField;
    @FXML
    private DatePicker durationField;
    @FXML
    private CheckBox activeCheckBox;
    @FXML
    private TableView<Producto> productTable;
    @FXML
    private TableColumn<Producto, Integer> idColumn;
    @FXML
    private TableColumn<Producto, Integer> quantityColumn;
    @FXML
    private TableColumn<Producto, Integer> minColumn;
    @FXML
    private TableColumn<Producto, Integer> maxColumn;
    @FXML
    private TableColumn<Producto, String> nameColumn;
    @FXML
    private TableColumn<Producto, String> typeColumn;
    @FXML
    private TableColumn<Producto, String> unitColumn;
    @FXML
    private TableColumn<Producto, String> noteColumn;
    @FXML
    private TableColumn<Producto, LocalDate> durationColumn;
    @FXML
    private TableColumn<Producto, Boolean> activeColumn;

    private final ObservableList<Producto> products = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (idColumn == null || nameColumn == null || typeColumn == null || unitColumn == null || noteColumn == null || durationColumn == null || quantityColumn == null || minColumn == null || maxColumn == null || activeColumn == null) {
            throw new IllegalStateException("FXML columns are not properly injected. Check fx:id attributes.");
        }

        products.addAll(ProductoDB.getAllProducts());  // Load products from DB
        productTable.setItems(products);

        idColumn.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(data -> data.getValue().nombreProperty());
        typeColumn.setCellValueFactory(data -> data.getValue().tipoProperty());
        unitColumn.setCellValueFactory(data -> data.getValue().unidadMedidaProperty());
        noteColumn.setCellValueFactory(data -> data.getValue().notaProperty());
        durationColumn.setCellValueFactory(data -> data.getValue().duracionEstimadaProperty());
        quantityColumn.setCellValueFactory(data -> data.getValue().cantidadProperty().asObject());
        minColumn.setCellValueFactory(data -> data.getValue().minProperty().asObject());
        maxColumn.setCellValueFactory(data -> data.getValue().maxProperty().asObject());
        activeColumn.setCellValueFactory(data -> data.getValue().activoProperty().asObject());

        productTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleRowSelection();
            }
        });
    }


    @FXML
    protected void handleRowSelection() {
        Producto selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            nameField.setText(selectedProduct.getNombre());
            typeField.setText(selectedProduct.getTipo());
            unitField.setText(selectedProduct.getUnidadMedida());
            noteField.setText(selectedProduct.getNota());
            durationField.setValue(selectedProduct.getDuracionEstimada());
            quantityField.setText(String.valueOf(selectedProduct.getCantidad()));
            minField.setText(String.valueOf(selectedProduct.getMin()));
            maxField.setText(String.valueOf(selectedProduct.getMax()));
            activeCheckBox.setSelected(selectedProduct.isActivo());
        }
    }

    @FXML
    protected void handleAddProduct() {
        try {
            if (areFieldsValid()) {
                Producto newProduct = createProductFromFields();
                ProductoDB.insertProduct(newProduct);
                products.clear();
                products.addAll(ProductoDB.getAllProducts());
                clearFields();
            } else {
                showAlert("Input Error", "All fields must be correctly filled.");
            }
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please ensure numeric fields are correctly filled.");
        }
    }

    @FXML
    protected void handleUpdateProduct() {
        Producto selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            try {
                updateProductFromFields(selectedProduct);
                ProductoDB.updateProduct(selectedProduct);
                productTable.refresh();
                clearFields();
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Please ensure numeric fields are correctly filled.");
            }
        } else {
            showAlert("Selection Error", "Please select a product to update.");
        }
    }

    @FXML
    protected void handleDeleteProduct() {
        Producto selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            ProductoDB.deleteProduct(selectedProduct.getId());
            products.remove(selectedProduct);
        } else {
            showAlert("Selection Error", "Please select a product to delete.");
        }
    }

    private boolean areFieldsValid() {
        return !nameField.getText().isEmpty() && !typeField.getText().isEmpty() &&
                !unitField.getText().isEmpty() && !noteField.getText().isEmpty() &&
                durationField.getValue() != null &&
                isNumeric(quantityField.getText()) && isNumeric(minField.getText()) && isNumeric(maxField.getText());
    }

    private Producto createProductFromFields() {
        return new Producto(
                nameField.getText(),
                typeField.getText(),
                unitField.getText(),
                noteField.getText(),
                durationField.getValue(),
                Integer.parseInt(quantityField.getText()),
                Integer.parseInt(minField.getText()),
                Integer.parseInt(maxField.getText()),
                activeCheckBox.isSelected()
        );
    }

    private void updateProductFromFields(Producto product) {
        product.setNombre(nameField.getText());
        product.setTipo(typeField.getText());
        product.setUnidadMedida(unitField.getText());
        product.setNota(noteField.getText());
        product.setDuracionEstimada(durationField.getValue());
        product.setCantidad(Integer.parseInt(quantityField.getText()));
        product.setMin(Integer.parseInt(minField.getText()));
        product.setMax(Integer.parseInt(maxField.getText()));
        product.setActivo(activeCheckBox.isSelected());
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void clearFields() {
        nameField.clear();
        typeField.clear();
        unitField.clear();
        noteField.clear();
        quantityField.clear();
        minField.clear();
        maxField.clear();
        durationField.setValue(null);
        activeCheckBox.setSelected(false);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
