package edu.upc.dsa.models;

public class Nivel {
    private String idNivel;
    private int numeroNivel;
    private int puntosNecesarios;
    private String descripcion;

    public Nivel(String idNivel, int numeroNivel, int puntosNecesarios, String descripcion) {
        this.idNivel = idNivel;
        this.numeroNivel = numeroNivel;
        this.puntosNecesarios = puntosNecesarios;
        this.descripcion = descripcion;
    }

    public String getIdentificador() {
        return idNivel;
    }

    public int getNumeroNivel() {
        return numeroNivel;
    }

    public int getPuntosNecesarios() {
        return puntosNecesarios;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
