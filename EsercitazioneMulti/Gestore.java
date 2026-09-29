package EsercitazioneMulti;

public class Gestore {
    private Coda<Processabile>[] code;

    public Gestore(int capacita) {
        code = (Coda<Processabile>[]) new Coda<?>[capacita];
    }

    private int trovaSpazio() {
        for (int i = 0; i < code.length; i++) {
            if (code[i] == null) return i;
        }
        return -1;
    }

    public void aggiungiCoda(Coda<Processabile> coda, int ID) {
        int spazio = trovaSpazio();
        if (spazio == -1) throw new IllegalArgumentException("Il gestore è pieno.");
        code[spazio] = new Coda<>(ID);
    }

    private Coda<Processabile> trovaCoda(int ID) {
        for (Coda<Processabile> coda : code) {
            if (coda.getID() == ID) return coda;
        }
        throw new IllegalArgumentException("ID elenco non valido");
    }

    public <T extends Generica> boolean inserisciRichiesta(int ID, T richiesta, int posizione) {
        Coda<? super T> destinazione = trovaCoda(ID);
        return destinazione.aggiungiElemento(richiesta, posizione);
    }

    public void evadiRichiesta(int ID, int posizione) {
        Coda<? extends Processabile> elenco = trovaCoda(ID);
        elenco.rimuoviElemento(posizione);
    }

    public void prossimaInLavorazione(int ID) {
        Coda<? extends Processabile> elenco = trovaCoda(ID);
        elenco.primoElemento();
    }

    public int cercaRichiesta(int ID, Processabile richiesta) {
        Coda<? extends Processabile> elenco = trovaCoda(ID);
        return elenco.cercaElemento(richiesta);
    }
}