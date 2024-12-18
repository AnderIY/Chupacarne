package com.example.erronka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    private final Connection connection;

    public LoginService() {
        ConnectDB.ConnectionDB connectionDB = new ConnectDB.ConnectionDB();
        this.connection = connectionDB.getConnection();
        if (this.connection != null) {
            System.out.println("Conexión a la base de datos establecida correctamente.");
        } else {
            System.out.println("Error: No se pudo establecer la conexión a la base de datos.");
        }
    }
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean verifyLogin(String username, String password) {
        String query = "SELECT COUNT(*) AS count FROM usuario WHERE usuario = ? AND contraseña = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    if (count > 0) {
                        System.out.println("Inicio de sesión exitoso para el usuario: " + username);
                        return true;
                    } else {
                        System.out.println("Credenciales incorrectas para el usuario: " + username);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void closeService() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexión a la base de datos cerrada correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
