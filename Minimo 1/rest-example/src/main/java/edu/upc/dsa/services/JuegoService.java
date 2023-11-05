package edu.upc.dsa.services;

import edu.upc.dsa.JuegoVirtualService;
import edu.upc.dsa.JuegoVirtualServiceImpl;
import edu.upc.dsa.models.Partida;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Api(value = "/partidas", description = "Endpoint to Partidas Service")
@Path("/")
public class JuegoService {

    private JuegoVirtualService jvs;

    public JuegoService() {
        this.jvs = JuegoVirtualServiceImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Crear un juego", notes = "Inicia un juego")
    @ApiResponses(value = {
            @ApiResponse(code = 209, message = "Juego creado con éxito"),
    })
    @Path("/{identificador}/{descripcion}/{numNiveles}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearJuego(@PathParam("identificador") String identificador, @PathParam("descripcion") String descripcion, @PathParam("numNiveles") int numNiveles) {
        this.jvs.crearJuego(identificador, descripcion, numNiveles);
        return Response.status(209).build();
    }

    @POST
    @ApiOperation(value = "Iniciar Partida", notes = "Iniciamos una partida en un juego")
    @ApiResponses(value = {
            @ApiResponse(code = 209, message = "Partida creada con exito"),
            @ApiResponse(code = 452, message = "Error al crear partida"),
    })
    @Path("/{idUsuario}/{idJuego}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response iniciarPartida(@PathParam("idUsuario") String idUsuario, @PathParam("idJuego") String idJuego) {
        try {
            this.jvs.iniciarPartida(idUsuario, idJuego);
        } catch (Exception e) {
            return Response.status(452).build();
        }
        return Response.status(209).build();
    }
    @GET
    @ApiOperation(value = "Consultar nivel ", notes = "Consultar en que nivel esta un Usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 209, message = "Nivel consultado con exito"),
            @ApiResponse(code = 452, message = "Error al consultar nivel"),
    })
    @Path("/{idUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consultarNivelActual(@PathParam("idUsuario") String idUsuario) {
        try {
            this.jvs.consultarNivelActual(idUsuario);
        } catch (Exception e) {
            return Response.status(452).build();
        }
        return Response.status(209).build();
    }
    @GET
    @ApiOperation(value = "Consultar puntuacion ", notes = "Consultar la puntuacion de un Usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 209, message = "Puntuacion consultado con exito"),
            @ApiResponse(code = 452, message = "Error al consultar puntuacion"),
    })
    @Path("/{idUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consultarPuntuacionActual(@PathParam("idUsuario") String idUsuario) {
        try {
            this.jvs.consultarPuntuacionActual(idUsuario);
        } catch (Exception e) {
            return Response.status(452).build();
        }
        return Response.status(209).build();
    }
    @PUT
    @ApiOperation(value = "Pasar Nivel", notes = "Pasar Nivel")
    @ApiResponses(value = {
            @ApiResponse(code = 209, message = "Pasaste de nivel con exito"),
            @ApiResponse(code = 452, message = "Error al pasar el nivel"),
    })
    @Path("/{idUsuario}/{puntos}/{fecha}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pasarNivel(@PathParam("idUsuario") String idUsuario, @PathParam("puntos") int puntos, @PathParam("fecha") Date fecha) {
        try {
            this.jvs.pasarNivel(idUsuario, puntos, fecha);
        } catch (Exception e) {
            return Response.status(452).build();
        }
        return Response.status(209).build();
    }
    @POST
    @ApiOperation(value = "Finalizar partida", notes = "Finalizar partida a partir de un Usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 209, message = "partida finalizada con exito"),
            @ApiResponse(code = 452, message = "Error al finalizar partida"),
    })
    @Path("/{idUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response finalizarPartida(@PathParam("idUsuario") String idUsuario) {
        try {
            this.jvs.finalizarPartida(idUsuario);
        } catch (Exception e) {
            return Response.status(452).build();
        }
        return Response.status(209).build();
    }
    @GET
    @ApiOperation(value = "Consultar usuarios que han jugado un juego ", notes = "Consultar lista usuarios que han jugado un juego ordenados por puntuacion (descendiente)")
    @ApiResponses(value = {
            @ApiResponse(code = 209, message = "lista obtenida exito"),
            @ApiResponse(code = 452, message = "Juego no encontrado"),
    })
    @Path("/{idJuego}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consultarUsuariosPorPuntuacion(@PathParam("idJuego") String idJuego) {
        try {
            this.jvs.consultarUsuariosPorPuntuacion(idJuego);
        } catch (Exception e) {
            return Response.status(452).build();
        }
        return Response.status(209).build();
    }
    @GET
    @ApiOperation(value = "Consultar usuarios que han jugado un juego ", notes = "Consultar lista usuarios que han jugado un juego ordenados por puntuacion (descendiente)")
    @ApiResponses(value = {
            @ApiResponse(code = 209, message = "lista obtenida exito"),
            @ApiResponse(code = 452, message = "Juego no encontrado"),
    })
    @Path("/{idUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consultarPartidasUsuario(@PathParam("idUsuario") String idUsuario) {
        try {
            this.jvs.consultarPartidasUsuario(idUsuario);
        } catch (Exception e) {
            return Response.status(452).build();
        }
        return Response.status(209).build();
    }
}