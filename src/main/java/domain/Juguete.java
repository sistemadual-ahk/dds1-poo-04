package domain;

public class Juguete implements Jugable {
    private int nivelDeDiversion;

    public Juguete(int nivelDeDiversion){
        this.nivelDeDiversion = nivelDeDiversion;
    }

    @Override
    public void jugarCon(Gato unGato){
        unGato.aumentarDiversionEn(this.nivelDeDiversion);
    }
}
