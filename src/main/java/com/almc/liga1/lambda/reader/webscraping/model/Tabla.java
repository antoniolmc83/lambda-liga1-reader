package com.almc.liga1.lambda.reader.webscraping.model;

import java.util.List;

//@JsonRootName("")
public class Tabla {

    private String fecha;
    private List<Posicion> tabla;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<Posicion> getTabla() {
        return tabla;
    }

    public void setTabla(List<Posicion> tabla) {
        this.tabla = tabla;
    }
}
