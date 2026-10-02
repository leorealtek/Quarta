package RipassoVerifica;

import java.util.NoSuchElementException;

public class Cella<T> implements Contenitore<T> {

    private T elemento;

    public Cella(T elemento) {
        this.elemento = elemento;
    }

    @Override
    public void inserisciElemento(T elemento) {
        if (!isVuoto()) throw new IllegalStateException("La cella è piena.");
        this.elemento = elemento;
    }

    @Override
    public T estraiElemento() {
        if (isVuoto()) throw new NoSuchElementException("La cella è vuota.");
        T temp = elemento;
        elemento = null;
        return temp;
    }

    @Override
    public boolean isVuoto() {
        return elemento == null;
    }
    
}