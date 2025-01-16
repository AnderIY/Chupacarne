package com.example.erronka;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Langilea {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty dni = new SimpleStringProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty apellido1 = new SimpleStringProperty();
    private final StringProperty apellido2 = new SimpleStringProperty();

    public Langilea(String dni, String nombre, String apellido1, String apellido2) {
        this.dni.set(dni);
        this.nombre.set(nombre);
        this.apellido1.set(apellido1);
        this.apellido2.set(apellido2);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty apellido1Property() {
        return apellido1;
    }

    public StringProperty apellido2Property() {
        return apellido2;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDni() {
        return dni.get();
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido1() {
        return apellido1.get();
    }

    public void setApellido1(String apellido1) {
        this.apellido1.set(apellido1);
    }

    public String getApellido2() {
        return apellido2.get();
    }

    public void setApellido2(String apellido2) {
        this.apellido2.set(apellido2);
    }
}
