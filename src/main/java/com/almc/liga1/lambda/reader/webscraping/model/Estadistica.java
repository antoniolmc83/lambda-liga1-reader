package com.almc.liga1.lambda.reader.webscraping.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Estadistica {

    private Integer puntos;
    @JsonProperty("p_jugados")
    private Integer partidosJugados;
    @JsonProperty("p_ganados")
    private Integer partidosGanados;
    @JsonProperty("p_empatados")
    private Integer partidosEmpatados;
    @JsonProperty("p_perdidos")
    private Integer partidosPerdidos;
    @JsonProperty("goles_favor")
    private Integer golesFavor;
    @JsonProperty("goles_contra")
    private Integer golesContra;
    @JsonProperty("goles_diff")
    private Integer golesDiferencia;


    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(Integer partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public Integer getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(Integer partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public Integer getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(Integer partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public Integer getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(Integer partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public Integer getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(Integer golesFavor) {
        this.golesFavor = golesFavor;
    }

    public Integer getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(Integer golesContra) {
        this.golesContra = golesContra;
    }

    public Integer getGolesDiferencia() {
        return golesDiferencia;
    }

    public void setGolesDiferencia(Integer golesDiferencia) {
        this.golesDiferencia = golesDiferencia;
    }
}
