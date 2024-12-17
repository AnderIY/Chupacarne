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
    }


    public boolean verifyLogin(String username, String password) {
        String query = "SELECT COUNT(*) AS count FROM usuario WHERE usuario = ? AND contraseña = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);

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


    public void closeService() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
