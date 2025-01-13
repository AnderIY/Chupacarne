package com.example.erronka;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import java.sql.Connection;

public class ErreserbakController {

    @FXML
    private TextField workerIdField;
    @FXML
    private TextField tableIdField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField clientDNIField;
    @FXML
    private TableView<Reservation> reservationsTable;
    @FXML
    private TableColumn<Reservation, Integer> workerColumn;
    @FXML
    private TableColumn<Reservation, Integer> tableColumn;
    @FXML
    private TableColumn<Reservation, String> dateColumn;
    @FXML
    private TableColumn<Reservation, String> timeColumn;
    @FXML
    private TableColumn<Reservation, String> clientDNIColumn;

    private ReservationDB reservationDB;

    @FXML
    public void initialize() {
        // Establecer las columnas de la tabla
        workerColumn.setCellValueFactory(new PropertyValueFactory<>("workerId"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("tableId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        clientDNIColumn.setCellValueFactory(new PropertyValueFactory<>("clientDNI"));

        // Crear la conexión a la base de datos y el objeto ReservationDB
        Connection connection = ConnectDB.getConnection();
        reservationDB = new ReservationDB(connection);
    }

    @FXML
    private void handleReservationAction(ActionEvent event) {
        // Lógica para crear una nueva reserva
        int workerId = Integer.parseInt(workerIdField.getText());  // ID del trabajador
        int tableId = Integer.parseInt(tableIdField.getText());    // ID de la mesa
        String date = dateField.getValue().toString();             // Fecha
        String time = timeField.getText();                         // Hora
        String clientDNI = clientDNIField.getText();               // DNI del cliente

        // Validar la hora (asegurarse de que el formato es correcto: HH:mm:ss)
        if (!time.matches("\\d{2}:\\d{2}:\\d{2}")) {
            System.out.println("El formato de la hora es incorrecto. Debe ser HH:mm:ss.");
            return;
        }

        // Crear una nueva instancia de Reserva
        Reservation newReservation = new Reservation(workerId, tableId, date, time, clientDNI);

        // Guardar la reserva en la base de datos
        if (reservationDB.agregarReserva(newReservation)) {
            // Si la reserva se guardó correctamente, agregarla a la tabla
            reservationsTable.getItems().add(newReservation);

            // Limpiar los campos después de agregar la reserva
            workerIdField.clear();
            tableIdField.clear();
            dateField.setValue(null);
            timeField.clear();
            clientDNIField.clear();
        } else {
            // Si ocurrió un error, mostrar un mensaje
            System.out.println("Error al agregar la reserva.");
        }
    }
}
