package RipassoVerifica;

public interface Filtro<T> {
    boolean accetta(T elemento);
}