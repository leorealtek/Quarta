package Lists.Merce;

import Lists.LinkedList;

public class Convoglio {
    private LinkedList<Locomotiva> lista;

    public Convoglio(Locomotiva testa, Locomotiva coda) {
        this.lista = new LinkedList<>();
        this.lista.add(testa);
        this.lista.add(coda);
    }

    public void aggiungiVagone(Vagone v) {
        lista.insert(v, 1);
    }

    public void stampaConvoglio() {
        lista.printLine();
    }

    public LinkedList<Locomotiva> getLista() {
        return lista;
    }

}