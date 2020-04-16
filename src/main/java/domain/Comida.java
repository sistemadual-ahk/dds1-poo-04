package domain;

public class Comida {
    private int nivelDeSaciedad;

    public Comida(int nivelDeSaciamiento){
        this.nivelDeSaciedad = nivelDeSaciamiento;
    }

    public void afectarHambreDe(Gato unGato, int raciones){
        unGato.disminuirHambreEn(raciones * this.nivelDeSaciedad);
    }
}
