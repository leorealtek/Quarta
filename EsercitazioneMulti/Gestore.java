package EsercitazioneMulti;

public class Gestore {
    private Coda<? extends Generico>[] code;

    public Gestore(int capacita) {
        code = (Coda<? extends Generico>[]) new Coda<?>[capacita];
    }

    public void aggiungiCoda(Coda<? extends Generico> coda) {
        int spazio = 0;
        for (; spazio < code.length; spazio++) {
            if (code[spazio] == null) break;
        }
        if (spazio == code.length) throw new IllegalArgumentException("Il gestore è pieno.");
        code[spazio] = coda;
    }

    public Coda<? extends Generico> trovaCodaDaID(int ID) {
        for (Coda<? extends Generico> coda : code) {
            if (coda == null) break;
            if (coda.getID() == ID) return coda;
        }
        throw new IllegalArgumentException("Coda con ID: " + ID + " non trovata.");
    }

    public <T extends Generico> boolean inserisciRichiesta(int ID, T richiesta, int posizione) {
        Coda<? super T> destinazione = (Coda<? super T>) trovaCodaDaID(ID);
        return destinazione.aggiungiElemento(richiesta, posizione);
    }

    public void evadiRichiesta(int ID, int posizione) {
        Coda<? extends Generico> elenco = trovaCodaDaID(ID);
        elenco.rimuoviElemento(posizione);
    }

    public void prossimaInLavorazione(int ID) {
        Coda<? extends Generico> elenco = trovaCodaDaID(ID);
        elenco.primoElemento();
    }

    public <T extends Generico> int cercaRichiesta(int ID, T richiesta) {
        Coda<? super T> elenco = (Coda<? super T>) trovaCodaDaID(ID);
        return elenco.cercaElemento(richiesta);
    }
}