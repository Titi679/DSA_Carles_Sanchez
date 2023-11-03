package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.models.Partida;

import java.util.Date;
import java.util.List;

public interface JuegoVirtualService {
    void crearJuego(String idJuego, String descripcion, int numNiveles);
    void iniciarPartida(String idUsuario, String idJuego);
    String consultarNivelActual(String idUsuario);
    int consultarPuntuacionActual(String idUsuario);
    void pasarNivel(String idUsuario, int puntos, Date fecha);
    void finalizarPartida(String nombreUsuario);
    List<Usuario> consultarUsuariosPorPuntuacion(String idJuego);
    List<Partida> consultarPartidasUsuario(String nombreUsuario);
}
