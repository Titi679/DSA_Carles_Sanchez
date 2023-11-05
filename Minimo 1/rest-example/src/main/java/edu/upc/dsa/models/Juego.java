package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private String idJuego;
    private String descripcion;
    private int numeroNiveles;
    private List<Partida> partidas;
    private static int numeroTotalJuegos = 0;

    public Juego(String idJuego, String descripcion, int numeroNiveles) {
        this.idJuego = idJuego;
        this.descripcion = descripcion;
        this.numeroNiveles = numeroNiveles;
        this.partidas = new ArrayList<>();
        numeroTotalJuegos++;
    }
    public String getIdentificador() {
        return idJuego;
    }
    public int getNumeroNiveles() {
        return numeroNiveles;
    }
    public List<Partida> getPartidasDeUsuario(Usuario usuario) {
        List<Partida> partidasDelUsuario = new ArrayList<>();
        for (Partida partida : partidas) {
            if (partida.getUsuario().equals(usuario)) {
                partidasDelUsuario.add(partida);
            }
        }
        return partidasDelUsuario;
    }
    public void agregarPartida(Partida partida) {
        partidas.add(partida);
    }
    public static int getNumeroTotalJuegos() {
        return numeroTotalJuegos;
    }
}
