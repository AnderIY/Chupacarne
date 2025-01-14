package com.example.erronka;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Producto {
    private final IntegerProperty id;
    private final StringProperty nombre;
    private final StringProperty tipo;
    private final StringProperty unidadMedida;
    private final StringProperty nota;
    private final ObjectProperty<LocalDate> duracionEstimada;
    private final IntegerProperty cantidad;
    private final IntegerProperty min;
    private final IntegerProperty max;
    private final BooleanProperty activo;

    // Constructor
    public Producto(String nombre, String tipo, String unidadMedida, String nota, LocalDate duracionEstimada, int cantidad, int min, int max, boolean activo) {
        this.id = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty(nombre);
        this.tipo = new SimpleStringProperty(tipo);
        this.unidadMedida = new SimpleStringProperty(unidadMedida);
        this.nota = new SimpleStringProperty(nota);
        this.duracionEstimada = new SimpleObjectProperty<>(duracionEstimada);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.activo = new SimpleBooleanProperty(activo);
    }

    // Property methods
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty tipoProperty() {
        return tipo;
    }

    public StringProperty unidadMedidaProperty() {
        return unidadMedida;
    }

    public StringProperty notaProperty() {
        return nota;
    }

    public ObjectProperty<LocalDate> duracionEstimadaProperty() {
        return duracionEstimada;
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    public IntegerProperty minProperty() {
        return min;
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    public BooleanProperty activoProperty() {
        return activo;
    }

    // Getters and Setters
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public String getUnidadMedida() {
        return unidadMedida.get();
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida.set(unidadMedida);
    }

    public String getNota() {
        return nota.get();
    }

    public void setNota(String nota) {
        this.nota.set(nota);
    }

    public LocalDate getDuracionEstimada() {
        return duracionEstimada.get();
    }

    public void setDuracionEstimada(LocalDate duracionEstimada) {
        this.duracionEstimada.set(duracionEstimada);
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public int getMin() {
        return min.get();
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public int getMax() {
        return max.get();
    }

    public void setMax(int max) {
        this.max.set(max);
    }

    public boolean isActivo() {
        return activo.get();
    }

    public void setActivo(boolean activo) {
        this.activo.set(activo);
    }
}