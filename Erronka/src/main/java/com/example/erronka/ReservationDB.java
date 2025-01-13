package com.example.erronka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationDB {

    private Connection connection;

    public ReservationDB(Connection connection) {
        this.connection = connection;
    }


    public boolean agregarReserva(Reservation reservation) {
        String query = "INSERT INTO reservas (id_trabajador, id_mesa, fecha, hora, dni_cliente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getWorkerId());
            statement.setInt(2, reservation.getTableId());
            statement.setString(3, reservation.getDate());
            statement.setString(4, reservation.getTime());
            statement.setString(5, reservation.getClientDNI());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
