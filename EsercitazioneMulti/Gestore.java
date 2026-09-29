package EsercitazioneMulti;

public class Gestore {
    private Coda<? extends Processabile>[] code;

    public Gestore(int capacita) {
        code = (Coda<? extends Processabile>[]) new Coda<?>[capacita];
    }

    public void aggiungiCoda(Coda<? extends Processabile> coda) {
        int spazio = 0;
        for (; spazio < code.length; spazio++) {
            if (code[spazio] == null) break;
        }
        if (spazio == code.length) throw new IllegalArgumentException("Il gestore è pieno.");
        code[spazio] = coda;
    }

    public Coda<? extends Processabile> trovaCodaDaID(int ID) {
        for (Coda<? extends Processabile> coda : code) {
            if (coda == null) break;
            if (coda.getID() == ID) return coda;
        }
        throw new IllegalArgumentException("Coda con ID: " + ID + " non trovata.");
    }

    public <T extends Processabile> boolean inserisciRichiesta(int ID, T richiesta, int posizione) {
        Coda<? super T> destinazione = (Coda<? super T>) trovaCodaDaID(ID);
        return destinazione.aggiungiElemento(richiesta, posizione);
    }

    public void evadiRichiesta(int ID, int posizione) {
        Coda<? extends Processabile> elenco = trovaCodaDaID(ID);
        elenco.rimuoviElemento(posizione);
    }

    public void prossimaInLavorazione(int ID) {
        Coda<? extends Processabile> elenco = trovaCodaDaID(ID);
        elenco.primoElemento();
    }

    public int cercaRichiesta(int ID, Processabile richiesta) {
        return trovaCodaDaID(ID).cercaElemento(richiesta);
    }
}