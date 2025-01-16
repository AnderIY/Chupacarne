package com.example.erronka.DB;

import com.example.erronka.Langilea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class LangileaDB {
    private static final String INSERT_QUERY = "INSERT INTO trabajador (dni, nombre, apellido1, apellido2) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM trabajador";
    private static final String UPDATE_QUERY = "UPDATE trabajador SET dni = ?, nombre = ?, apellido1 = ?, apellido2 = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM trabajador WHERE id = ?";

    public static ObservableList<Langilea> getAllLangileak() {
        ObservableList<Langilea> langileak = FXCollections.observableArrayList();

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Langilea langilea = new Langilea(
                        resultSet.getString("dni"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido1"),
                        resultSet.getString("apellido2")
                );
                langilea.setId(resultSet.getInt("id"));
                langileak.add(langilea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return langileak;
    }

    public static void insertLangilea(Langilea langilea) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, langilea.getDni());
            statement.setString(2, langilea.getNombre());
            statement.setString(3, langilea.getApellido1());
            statement.setString(4, langilea.getApellido2());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    langilea.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateLangilea(Langilea langilea) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {

            statement.setString(1, langilea.getDni());
            statement.setString(2, langilea.getNombre());
            statement.setString(3, langilea.getApellido1());
            statement.setString(4, langilea.getApellido2());
            statement.setInt(5, langilea.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteLangilea(int langileaId) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {

            statement.setInt(1, langileaId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
