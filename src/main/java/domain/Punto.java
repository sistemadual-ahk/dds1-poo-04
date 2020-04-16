package domain;

public class Punto {
    private int posicionX;
    private int posicionY;

    public Punto(int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public int distanciaAbsolutaEntre(Punto otroPunto){
        int distanciaEnX = this.diferenciaAlCuadradoEntre(this.posicionX, otroPunto.getPosicionX());
        int distanciaEnY = this.diferenciaAlCuadradoEntre(this.posicionY, otroPunto.getPosicionY());
        return (int) Math.sqrt(distanciaEnX + distanciaEnY);
    }

    private int diferenciaAlCuadradoEntre(int unPunto, int otroPunto){
        return (unPunto - otroPunto)*(unPunto - otroPunto);
    }
}
