package com.example.erronka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {


    public static class ModifyDB {
        public static void updateRecord(Connection connection, String tableName, String columnName, Object newValue, String condition) throws SQLException {
            String query = "UPDATE " + tableName + " SET " + columnName + " = ? WHERE " + condition;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setObject(1, newValue);
                statement.executeUpdate();
            }
        }
    }


    public static class InsertDB {
        public static void insertRecord(Connection connection, String tableName, List<String> columns, List<Object> values) throws SQLException {
            if (columns.size() != values.size()) {
                throw new IllegalArgumentException("Columns and values size must match.");
            }

            String columnNames = String.join(", ", columns);
            String placeholders = String.join(", ", columns.stream().map(c -> "?").toArray(String[]::new));

            String query = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES (" + placeholders + ")";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 0; i < values.size(); i++) {
                    statement.setObject(i + 1, values.get(i));
                }
                statement.executeUpdate();
            }
        }
    }


    public static class DeleteDB {
        public static void deleteRecord(Connection connection, String tableName, String condition) throws SQLException {
            String query = "DELETE FROM " + tableName + " WHERE " + condition;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            }
        }
    }

    public static class QueryDB {
        public static List<String> queryRecords(Connection connection, String query, String columnLabel) throws SQLException {
            List<String> results = new ArrayList<>();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    results.add(resultSet.getString(columnLabel));
                }
            }
            return results;
        }
    }


    public static class ConnectionDB {

        private Connection connection;

        public ConnectionDB() {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/mydb",
                        "root",
                        "1WMG2023"
                );
                System.out.println("Conexión a la base de datos exitosa.");
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
                throw new RuntimeException("Error connecting to the database", e);
            }
        }
        public Connection getConnection() {
            return connection;
        }

        public void closeConnection() {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Error closing the database connection", e);
                }
            }
        }
        }



    }

