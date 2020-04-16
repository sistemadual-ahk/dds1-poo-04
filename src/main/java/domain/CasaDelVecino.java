package domain;

public class CasaDelVecino extends Lugar {
    private int cantidadVisitas;

    public CasaDelVecino(Punto punto) {
        super(punto);
        this.cantidadVisitas = 0;
    }

    public int getCantidadVisitas() {
        return cantidadVisitas;
    }

    @Override
    public void serVisitadoPor(Gato unGato){
        this.cantidadVisitas++;
    }
}
