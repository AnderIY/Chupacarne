package com.example.erronka.DB;

import com.example.erronka.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDB {

    private Connection connection;

    public UsuarioDB() {
        this.connection = ConnectDB.getConnection();  // Usar ConnectDB para obtener la conexión
    }

    public boolean verificarLogin(Usuario usuario) {
        String query = "SELECT COUNT(*) AS count FROM usuario WHERE usuario = ? AND contraseña = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getPassword());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
