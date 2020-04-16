package domain;

public class Prenda {
    private double nivelDeAbrigo;

    public Prenda(double nivelDeAbrigo){
        this.nivelDeAbrigo = nivelDeAbrigo;
    }

    public void abrigarA(Gato unGato){
        unGato.aumentarTemperaturaEn(this.nivelDeAbrigo);
    }

    public void quitateDe(Gato unGato){
        unGato.disminuirTemperaturaEn(this.nivelDeAbrigo);
    }
}
