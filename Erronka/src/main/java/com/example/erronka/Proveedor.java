package com.example.erronka;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Proveedor {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty cif = new SimpleStringProperty();
    private final StringProperty direccion = new SimpleStringProperty();
    private final StringProperty kontuKorrontea = new SimpleStringProperty();

    public Proveedor(String cif, String direccion, String kontuKorrontea) {
        this.cif.set(cif);
        this.direccion.set(direccion);
        this.kontuKorrontea.set(kontuKorrontea);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty cifProperty() {
        return cif;
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public StringProperty kontuKorronteaProperty() {
        return kontuKorrontea;
    }


    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getCif() {
        return cif.get();
    }

    public void setCif(String cif) {
        this.cif.set(cif);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getKontuKorrontea() {
        return kontuKorrontea.get();
    }

    public void setKontuKorrontea(String kontuKorrontea) {
        this.kontuKorrontea.set(kontuKorrontea);
    }


}