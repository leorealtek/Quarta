package RipassoVerifica;

public class CalcolatoreDispositivi {
    public static int sommaConsumi(Cella<? extends Dispositivo> c1, Cella<? extends Dispositivo> c2) {
        return c1.estraiElemento().getConsumoWatt() + c2.estraiElemento().getConsumoWatt();
    }

    public static <T extends Dispositivo> void trasferisci(Cella<T> c1, Cella<? super T> c2) {
        c2.inserisciElemento(c1.estraiElemento());
    }
}