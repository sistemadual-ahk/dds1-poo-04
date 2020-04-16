package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GatoTest {
    private Gato gary;

    @Before
    public void init(){
        this.gary = new Gato("Gary", Color.NARANJA);
    }

    @Test
    public void garyVisitaDosLugaresTest(){
        Lugar laCasaDePedro = new CasaDelVecino(new Punto(180,50));
        Lugar cocina = new Cocina(new Punto(15,14));

        Visita visitaAPedro = new Visita(laCasaDePedro);
        Visita visitaALaCocina = new Visita(cocina);

        this.gary.realizaVisita(visitaAPedro);
        this.gary.realizaVisita(visitaALaCocina);

        Assert.assertEquals(2, this.gary.cantidadDeVisitas());
        Assert.assertEquals(354, this.gary.metrosRecorridos());
    }
}
