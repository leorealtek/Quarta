package RipassoVerifica;

public interface Contenitore<T> {
    void inserisciElemento(T elemento);
    T estraiElemento();
    boolean isVuoto();
}