package domain;

public class Persona implements Jugable {
    private String nombre;

    public Persona(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void jugarCon(Gato unGato){
        unGato.aumentarDiversionEn(15);
        unGato.disminuirEnergiaEn(2);
    }
}
