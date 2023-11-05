package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.models.Partida;

import java.util.Date;
import java.util.List;

public interface JuegoVirtualService {
    void crearJuego(String idJuego, String descripcion, int numNiveles);
    void iniciarPartida(String idUsuario, String idJuego) throws Exception;
    int consultarNivelActual(String idUsuario) throws Exception;
    int consultarPuntuacionActual(String idUsuario) throws Exception;
    void pasarNivel(String idUsuario, int puntos, Date fecha) throws Exception;
    void finalizarPartida(String idUsuario) throws Exception;
    List<Usuario> consultarUsuariosPorPuntuacion(String idJuego) throws Exception;
    List<Partida> consultarPartidasUsuario(String idUsuario) throws Exception;
}
