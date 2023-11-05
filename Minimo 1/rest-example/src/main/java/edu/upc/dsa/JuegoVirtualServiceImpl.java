package edu.upc.dsa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.models.Partida;
import org.apache.log4j.Logger;



public class JuegoVirtualServiceImpl implements JuegoVirtualService {
    private List<Juego> juegos;
    private List<Usuario> usuarios;
    private List<Partida> partidas;
    private static JuegoVirtualServiceImpl instance = null;
    private static final Logger logger = Logger.getLogger(JuegoVirtualServiceImpl.class);


    public JuegoVirtualServiceImpl() {
        juegos = new ArrayList<>();
        usuarios = new ArrayList<>();
        partidas = new ArrayList<>();
    }

    public static JuegoVirtualServiceImpl getInstance() {
        if (instance == null) {
            instance = new JuegoVirtualServiceImpl();
        }
        return instance;
    }

    @Override
    public void crearJuego(String identificador, String descripcion, int numNiveles) {
        Juego juego = new Juego(identificador, descripcion, numNiveles);
        juegos.add(juego);
        logger.info("Juego creado: " + identificador);
    }

    @Override
    public void iniciarPartida(String idUsuario, String idJuego) throws Exception {
        Usuario usuario = encontrarUsuarioPorID(idUsuario);
        Juego juego = encontrarJuegoPorIdentificador(idJuego);

        if (usuario != null && juego != null) {
            if (usuario.getPartidaActual() == null) {
                Partida partida = new Partida(generarIdentificadorUnico(), juego, usuario, new Date());
                usuario.setPartidaActual(partida);
                juego.agregarPartida(partida);
                usuario.sumarPuntuacion(50);
                logger.info(idUsuario + " ha iniciado una partida en " + idJuego);
            } else if (usuario.getPartidaActual().getJuego() == juego) {
                logger.error("El usuario ya tiene una partida en curso del mismo juego.");
                throw new Exception("Partida en cursom");
            } else if (usuario.getPartidaActual().getNivelActual() > 1) {
                // El usuario ha terminado un juego antes de comenzar otro.
                Juego juegoAnterior = usuario.getPartidaActual().getJuego();
                logger.info(idUsuario + " después de terminar el juego " + juegoAnterior.getIdentificador() + " empieza el juego " + idJuego);
                Partida partida = new Partida(generarIdentificadorUnico(), juego, usuario, new Date());
                usuario.setPartidaActual(partida);
                partidas.add(partida);
            } else {
                logger.error("El usuario no puede comenzar una nueva partida si no ha terminado la anterior.");
                throw new Exception("Partida en curso");
            }
        } else {
            logger.error("Usuario o juego no encontrado.");
            throw new Exception("Usuario/Juego no existen");
        }
    }

    @Override
    public int consultarNivelActual(String idUsuario) throws Exception {
        Usuario usuario = encontrarUsuarioPorID(idUsuario);
        if (usuario != null) {
            Partida partidaActual = usuario.getPartidaActual();

            if (partidaActual != null) {
                int nivelActual = partidaActual.getNivelActual();
                return nivelActual;
            } else {
                // Si el usuario no está en una partida en curso, puedes devolver un valor predeterminado o lanzar una excepción.
                // Por ejemplo, puedes devolver -1 para indicar que no está en una partida.
                throw new Exception("Usuario no esta en partida");
            }
        } else {
            // Si el usuario no se encuentra, puedes devolver otro valor predeterminado o lanzar una excepción.
            // Por ejemplo, puedes devolver -2 para indicar que el usuario no se encontró.
            throw new Exception("Usuario no encontrado");
        }
    }


    @Override
    public int consultarPuntuacionActual(String idUsuario) throws Exception {
        Usuario usuario = encontrarUsuarioPorID(idUsuario);
        if (usuario != null) {
            Partida partida = usuario.getPartidaActual();
            if (partida != null) {
                return partida.getPuntuacionAcumulada();
            } else {
                logger.error("El usuario no tiene una partida en curso.");
                throw new Exception("El usuario no tiene una partida en curso");
            }
        } else {
            logger.error("Usuario no encontrado.");
            throw new Exception("Usuario no encontrado");
        }
    }

    @Override
    public void pasarNivel(String idUsuario, int puntos, Date fecha) throws Exception {
        Usuario usuario = encontrarUsuarioPorID(idUsuario);

        if (usuario != null) {
            Partida partida = usuario.getPartidaActual();
            if (partida != null) {
                int nivelActual = partida.getNivelActual();
                if (nivelActual < partida.getJuego().getNumeroNiveles()) {
                    // Calcula los puntos necesarios para pasar al siguiente nivel
                    int puntosNecesarios = puntosParaSiguienteNivel(nivelActual, partida.getJuego());

                    if (puntos >= puntosNecesarios) {
                        // El usuario tiene suficientes puntos para avanzar al siguiente nivel
                        partida.pasarNivel(puntos); // Llama al método en la clase Partida
                        usuario.sumarPuntuacion(puntos);

                        if (nivelActual != partida.getJuego().getNumeroNiveles()) {
                            logger.info(idUsuario + " ha pasado al nivel " + (nivelActual + 1) + " el día " + partida.getFecha());
                        }
                    } else {
                        logger.error("El usuario no tiene suficientes puntos para avanzar al siguiente nivel.");
                        throw new Exception("El usuario no tiene suficientes puntos para avanzar al siguiente nivel.");
                    }
                } else {
                    usuario.sumarPuntuacion(100);
                    usuario.setPartidaActual(null);
                    partidas.remove(partida);
                    logger.info(idUsuario + " ha finalizado la partida en " + partida.getJuego().getIdentificador());
                }
            } else {
                logger.error("El usuario no está en una partida en curso.");
                throw new Exception("El usuario no está en una partida en curso.");
            }
        } else {
            logger.error("Usuario no encontrado.");
            throw new Exception("Usuario no encontrado");
        }
    }

    @Override
    public void finalizarPartida(String idUsuario) throws Exception {
        Usuario usuario = encontrarUsuarioPorID(idUsuario);
        if (usuario != null) {
            Partida partida = usuario.getPartidaActual();
            if (partida != null) {
                usuario.setPartidaActual(null);
                logger.info(idUsuario + " ha finalizado la partida.");
            } else {
                logger.error("El usuario no tiene una partida en curso.");
                throw new Exception("El usuario no tiene una partida en curso.");
            }
        } else {
            logger.error("Usuario no encontrado.");
            throw new Exception("Usuario no encontrado");
        }
    }

    @Override
    public List<Usuario> consultarUsuariosPorPuntuacion(String idJuego) throws Exception {
        Juego juego = encontrarJuegoPorIdentificador(idJuego);

        if (juego != null) {
            List<Usuario> usuariosDelJuego = new ArrayList<>();

            for (Partida partida : partidas) {
                if (partida.getJuego() == juego) {
                    Usuario usuario = partida.getUsuario();
                    if (!usuariosDelJuego.contains(usuario)) {
                        usuariosDelJuego.add(usuario);
                    }
                }
            }
            usuariosDelJuego.sort(Comparator.comparing(Usuario::getPuntuacion).reversed());
            return usuariosDelJuego;
        } else {
            logger.error("Juego no encontrado.");
            throw new Exception("Juego no encontrado");
        }
    }

    @Override
    public List<Partida> consultarPartidasUsuario(String idUsuario) throws Exception {
        Usuario usuario = encontrarUsuarioPorID(idUsuario);
        if (usuario != null) {
            List<Partida> partidasUsuario = new ArrayList<>();
            if (usuario != null) {
                for (Juego juego : juegos) {
                    List<Partida> partidasDeUsuarioEnJuego = juego.getPartidasDeUsuario(usuario);
                    partidasUsuario.addAll(partidasDeUsuarioEnJuego);
                }
            }
            return partidasUsuario;
        } else {
            logger.error("Juego no encontrado.");
            throw new Exception("Usuario no encontrado");
        }
    }

    private String generarIdentificadorUnico() {
        return "ID_" + System.currentTimeMillis();
    }

    private Usuario encontrarUsuarioPorID(String idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdentificador().equals(idUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    private Juego encontrarJuegoPorIdentificador(String idJuego) {
        for (Juego juego : juegos) {
            if (juego.getIdentificador().equals(idJuego)) {
                return juego;
            }
        }
        return null;
    }

    private int puntosParaSiguienteNivel(int nivelActual, Juego juego) {
        if (nivelActual >= 1 && nivelActual < juego.getNumeroNiveles()) {
            return 1000; // Ejemplo: 1000 puntos para avanzar al siguiente nivel
        }
        return 0;
    }

    public int getNumeroTotalDeJuegos() {
        return Juego.getNumeroTotalJuegos();
    }

    public int getNivelActualPorIdUsuario(String idUsuario) {
        for (Juego juego : juegos) {
            List<Partida> partidas = juego.getPartidasDeUsuario(encontrarUsuarioPorID(idUsuario));
            for (Partida partida : partidas) {
                if (partida.getUsuario().getIdentificador().equals(idUsuario)) {
                    return partida.getNivelActual();
                }
            }
        }
        return -1;
    }
    public Usuario crearUsuario(String idUsuario, String nombre) {
        Usuario usuario = new Usuario(idUsuario, nombre);
        usuarios.add(usuario); // Asumiendo que tienes una lista de usuarios
        return usuario;
    }
}
