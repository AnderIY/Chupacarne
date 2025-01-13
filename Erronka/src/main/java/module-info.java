module com.example.erronka {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.erronka to javafx.fxml;
    exports com.example.erronka;
    exports com.example.erronka.DB;
    opens com.example.erronka.DB to javafx.fxml;
    exports com.example.erronka.Controller;
    opens com.example.erronka.Controller to javafx.fxml;
}