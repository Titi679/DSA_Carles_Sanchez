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

@Api(value = "/partidas", description = "Endpoint to Partidas Service")
@Path("/")
public class JuegoService {

    private JuegoVirtualService jvs;

    public JuegoService() {
        this.jvs = JuegoVirtualServiceImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Crear una partida", notes = "Inicia una nueva partida para un usuario en un juego")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Partida creada con éxito", response = Partida.class),
    })
    @Path("/{identificador}, /{descripcion}, /{numNiveles}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearPartida(@PathParam("identificador") String identificador, @PathParam("descripcion") String descripcion, @PathParam("numNiveles") String numNiveles) {
        return Response.status(201).build();
    }
    @POST
    @ApiOperation(value = "Crear una partida", notes = "Inicia una nueva partida para un usuario en un juego")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Partida creada con éxito", response = Partida.class),
    })
    @Path("/{identificador}, /{descripcion}, /{numNiveles}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearPartida(@PathParam("identificador") String identificador, @PathParam("descripcion") String descripcion, @PathParam("numNiveles") String numNiveles) {
        return Response.status(201).build();
    }
}
