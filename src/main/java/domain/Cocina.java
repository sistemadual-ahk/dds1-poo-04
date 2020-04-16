package domain;

public class Cocina extends Lugar {
    private int cantidadDePelos;

    public Cocina(Punto punto) {
        super(punto);
        this.cantidadDePelos = 1;
    }

    public int getCantidadDePelos() {
        return cantidadDePelos;
    }

    @Override
    public void serVisitadoPor(Gato unGato){
        this.cantidadDePelos++;
    }
}
