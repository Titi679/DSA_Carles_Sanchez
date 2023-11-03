package edu.upc.dsa.models;

import java.util.Date;

public class Partida {
    private String idPartida;
    private Juego juego;
    private Usuario usuario;
    private int nivelActual;
    private int puntuacionAcumulada;
    private Date fecha;

    public Partida(String idPartida, Juego juego, Usuario usuario, Date fechaInicio) {
        this.idPartida = idPartida;
        this.juego = juego;
        this.usuario = usuario;
        this.nivelActual = 1; // Comienza en el primer nivel
        this.puntuacionAcumulada = 0;
        this.fecha = fecha;
    }
    public Juego getJuego() {
        return juego;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public int getNivelActual() {
        return nivelActual;
    }
    public int getPuntuacionAcumulada() {
        return puntuacionAcumulada;
    }
    public void pasarNivel(int puntos) {
        nivelActual++;
        puntuacionAcumulada += puntos;
    }
    public Date getFecha() {
        return fecha;
    }
}
