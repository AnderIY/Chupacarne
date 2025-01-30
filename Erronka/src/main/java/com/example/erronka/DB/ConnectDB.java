package com.example.erronka.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    private static final String URL = "jdbc:mysql://192.168.115.154:3306/erronka";
    private static final String USER = "ander";
    private static final String PASSWORD = "ander";


    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.err.println("Error al intentar conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Cierra la conexión a la base de datos.
     * @param connection la conexión que se va a cerrar.
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
