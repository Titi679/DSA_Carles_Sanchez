package edu.upc.dsa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertTrue;

public class TestJuego {
    private JuegoVirtualServiceImpl jvs = new JuegoVirtualServiceImpl();

    @BeforeEach
    void SetUp(){
        this.jvs.crearUsuario("Titi679", "Carles");
    }

    @AfterEach
void tearDown(){
        this.jvs = null;
    }

    @Test
    void crearJuego(){
        this.jvs.crearJuego("Hollow Knight", "Metroidvania", 10);
        Assert.assertEquals(1,this.jvs.getNumeroTotalDeJuegos());
        this.jvs.crearJuego("Viva Piñata", "Juego ds", 1);
        Assert.assertEquals(1,this.jvs.getNumeroTotalDeJuegos());
    }
    @Test
    void consultarNivelActual() throws Exception {
        this.jvs.consultarNivelActual("Titi");
        Assert.assertEquals(1,this.jvs.getNivelActualPorIdUsuario("Titi"));
    }
    @Test
    void consultarPuntuacionActual() throws Exception {
        this.jvs.consultarNivelActual("Titi");
        Assert.assertEquals(50, this.jvs.getNivelActualPorIdUsuario("Titi"));
    }
}
