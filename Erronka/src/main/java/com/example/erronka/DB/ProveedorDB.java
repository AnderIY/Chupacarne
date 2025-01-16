package com.example.erronka.DB;

import com.example.erronka.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ProveedorDB {
    private static final String INSERT_QUERY = "INSERT INTO proveedor (cif, direccion, kontuKorrontea) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM proveedor";
    private static final String UPDATE_QUERY = "UPDATE proveedor SET cif = ?, direccion = ?, kontuKorrontea = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM proveedor WHERE id = ?";

    public static ObservableList<Proveedor> getAllProveedores() {
        ObservableList<Proveedor> proveedores = FXCollections.observableArrayList();

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Proveedor proveedor = new Proveedor(
                        resultSet.getString("cif"),
                        resultSet.getString("direccion"),
                        resultSet.getString("kontuKorrontea")

                );
                proveedor.setId(resultSet.getInt("id"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proveedores;
    }

    public static void insertProveedor(Proveedor proveedor) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, proveedor.getCif());
            statement.setString(2, proveedor.getDireccion());
            statement.setString(3, proveedor.getKontuKorrontea());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    proveedor.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProveedor(Proveedor proveedor) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {

            statement.setString(1, proveedor.getCif());
            statement.setString(2, proveedor.getDireccion());
            statement.setString(3, proveedor.getKontuKorrontea());
            statement.setInt(4, proveedor.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProveedor(int proveedorId) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {

            statement.setInt(1, proveedorId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
