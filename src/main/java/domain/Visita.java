package domain;

import java.time.LocalDate;

public class Visita {
    private Lugar lugar;
    private LocalDate fechaYHora;
    private int distanciaRecorrida;

    public Visita(Lugar lugar){
        this.lugar = lugar;
    }

    private void marcarFechaYHora(){
        this.fechaYHora = LocalDate.now();
    }

    public void concretarPor(Gato unGato){
        marcarFechaYHora();
        this.distanciaRecorrida = unGato.caminaHacia(this.lugar);
    }

    public int getDistanciaRecorrida() {
        return distanciaRecorrida;
    }
}
