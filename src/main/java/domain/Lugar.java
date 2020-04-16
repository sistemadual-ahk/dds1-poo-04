package domain;

public abstract class Lugar {
    private Punto punto;

    protected Lugar(Punto punto){
        this.punto = punto;
    }

    public Punto getPunto() {
        return punto;
    }

    public void serVisitadoPor(Gato unGato){

    }
}
