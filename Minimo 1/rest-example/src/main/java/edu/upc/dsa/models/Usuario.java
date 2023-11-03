package edu.upc.dsa.models;

public class Usuario {
    private String idUsuario;
    private String nombre;
    private int puntuacion;
    private Partida partidaActual;

    public Usuario(String idUsuario, String nombre) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.puntuacion = 0;
        this.partidaActual = null;
    }

    public String getIdentificador() {
        return idUsuario;
    }
    public int getPuntuacion() {
        return puntuacion;
    }
    public Partida getPartidaActual() {
        return partidaActual;
    }
    public void setPartidaActual(Partida partida) {
        this.partidaActual = partida;
    }
    public void sumarPuntuacion(int puntos) {
        this.puntuacion += puntos;
    }
}
