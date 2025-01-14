package com.example.erronka.DB;

import com.example.erronka.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ProductoDB {
    private static final String INSERT_QUERY = "INSERT INTO producto (nombre, tipo, unidadMedida, nota, duracionEstimada, cantidad, min, max, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM producto";
    private static final String UPDATE_QUERY = "UPDATE producto SET nombre = ?, tipo = ?, unidadMedida = ?, nota = ?, duracionEstimada = ?, cantidad = ?, min = ?, max = ?, activo = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM producto WHERE id = ?";

    public static ObservableList<Producto> getAllProducts() {
        ObservableList<Producto> products = FXCollections.observableArrayList();

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Producto product = new Producto(
                        resultSet.getString("nombre"),
                        resultSet.getString("tipo"),
                        resultSet.getString("unidadMedida"),
                        resultSet.getString("nota"),
                        resultSet.getDate("duracionEstimada").toLocalDate(),
                        resultSet.getInt("cantidad"),
                        resultSet.getInt("min"),
                        resultSet.getInt("max"),
                        resultSet.getBoolean("activo")
                );
                product.setId(resultSet.getInt("id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public static int getLastInsertedId() {
        int lastId = -1;
        String query = "SELECT LAST_INSERT_ID()";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                lastId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastId;
    }


    public static void insertProduct(Producto product) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, product.getNombre());
            statement.setString(2, product.getTipo());
            statement.setString(3, product.getUnidadMedida());
            statement.setString(4, product.getNota());
            statement.setDate(5, Date.valueOf(product.getDuracionEstimada()));
            statement.setInt(6, product.getCantidad());
            statement.setInt(7, product.getMin());
            statement.setInt(8, product.getMax());
            statement.setBoolean(9, product.isActivo());
            statement.executeUpdate();

            // Obtener el último ID insertado y asignarlo al producto
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateProduct(Producto product) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {

            statement.setString(1, product.getNombre());
            statement.setString(2, product.getTipo());
            statement.setString(3, product.getUnidadMedida());
            statement.setString(4, product.getNota());
            statement.setDate(5, Date.valueOf(product.getDuracionEstimada()));
            statement.setInt(6, product.getCantidad());
            statement.setInt(7, product.getMin());
            statement.setInt(8, product.getMax());
            statement.setBoolean(9, product.isActivo());
            statement.setInt(10, product.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(int productId) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {

            statement.setInt(1, productId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
