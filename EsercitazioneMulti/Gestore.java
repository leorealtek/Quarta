package EsercitazioneMulti;

public class Gestore<T extends Processabile> {
    private Coda<T>[] code;

    public Gestore(int[] IDCode) {
        code = (Coda<T>[]) new Coda<?>[IDCode.length];

        for (int i = 0; i < IDCode.length; i++) {
            for (int j = 0; j < i; j++) {
                if (IDCode[i] == IDCode[j]) {
                    throw new IllegalArgumentException("ID duplicato");
                }
            }
            code[i] = new Coda<>(IDCode[i]);
        }
    }

    private Coda<T> trovaCoda(int ID) {
        for (Coda<T> coda : code) {
            if (coda.getID() == ID) return coda;
        }
        throw new IllegalArgumentException("ID elenco non valido");
    }

    public boolean inserisciRichiesta(int ID, T richiesta, int posizione) {
        Coda<T> destinazione = trovaCoda(ID);
        return destinazione.aggiungiElemento(richiesta, posizione);
    }

    public void evadiRichiesta(int id, int posizione) {
        Coda<T> elenco = trovaCoda(id);
        elenco.rimuoviElemento(posizione);
    }

    public void prossimaInLavorazione(int id) {
        Coda<T> elenco = trovaCoda(id);
        elenco.primoElemento();
    }

    public int cercaRichiesta(int id, T richiesta) {
        Coda<T> elenco = trovaCoda(id);
        return elenco.cercaElemento(richiesta);
    }
}