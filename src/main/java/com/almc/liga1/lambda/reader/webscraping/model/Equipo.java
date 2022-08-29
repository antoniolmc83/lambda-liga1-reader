package com.almc.liga1.lambda.reader.webscraping.model;

public class Equipo {

    private String nombre;
    private Estadistica estadistica;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estadistica getEstadistica() {
        return estadistica;
    }

    public void setEstadistica(Estadistica estadistica) {
        this.estadistica = estadistica;
    }
}
