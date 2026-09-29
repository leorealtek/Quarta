package EsercitazioneMulti;

public class Gestore {
    private Coda<Processabile>[] code;

    public Gestore(int capacita) {
        code = (Coda<Processabile>[]) new Coda<?>[capacita];
    }

    public void aggiungiCoda(Coda<Processabile> coda) {
        int spazio = 0;
        for (; spazio < code.length; spazio++) {
            if (code[spazio] == null) break;
        }
        if (spazio == code.length) throw new IllegalArgumentException("Il gestore è pieno.");
        code[spazio] = coda;
    }

    private Coda<Processabile> trovaCoda(int ID) {
        for (Coda<Processabile> coda : code) {
            if (coda == null) break;
            if (coda.getID() == ID) return coda;
        }
        throw new IllegalArgumentException("Coda con ID: " + ID + " non trovata.");
    }

    public <T extends Generico> boolean inserisciRichiesta(int ID, T richiesta, int posizione) {
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