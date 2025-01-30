package com.example.erronka.Controller;

import com.example.erronka.DB.ConnectDB;
import com.example.erronka.DB.ReservationDB;
import com.example.erronka.Reservation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.sql.Connection;
import static com.example.erronka.Controller.BaseController.navigateToMainMenu;


public class ErreserbakController implements StageAwareController {

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
    private Stage usingStage;
    @FXML
    private Button backButton;

    @FXML
    public void handleBackButton() {
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        BaseController.navigateToMainMenu(currentStage);
    }
    @FXML
    public void initialize() {

        workerColumn.setCellValueFactory(new PropertyValueFactory<>("workerId"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("tableId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        clientDNIColumn.setCellValueFactory(new PropertyValueFactory<>("clientDNI"));


        Connection connection = ConnectDB.getConnection();
        reservationDB = new ReservationDB(connection);
    }

    @FXML
    private void handleReservationAction(ActionEvent event) {
        // Lógica para crear una nueva reserva
        try {
            int workerId = Integer.parseInt(workerIdField.getText());
            int tableId = Integer.parseInt(tableIdField.getText());
            String date = dateField.getValue().toString();
            String time = timeField.getText();
            String clientDNI = clientDNIField.getText();

            // Validar la hora (asegurarse de que el formato es correcto: HH:mm:ss)
            if (!time.matches("\\d{2}:\\d{2}:\\d{2}")) {
                System.out.println("El formato de la hora es incorrecto. Debe ser HH:mm:ss.");
                return;
            }


            Reservation newReservation = new Reservation(workerId, tableId, date, time, clientDNI);

            if (reservationDB.agregarReserva(newReservation)) {

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
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese valores válidos.");
        }
    }

    @Override
    public void setStage(Stage stage) {
        this.usingStage = stage;
    }
}
