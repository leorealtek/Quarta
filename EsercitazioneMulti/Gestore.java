package EsercitazioneMulti;

public class Gestore {
    private Coda<? extends Processabile>[] code;

    public Gestore(int[] IDCode) {
        code = (Coda<? extends Processabile>[]) new Coda<?>[IDCode.length];

        for (int i = 0; i < IDCode.length; i++) {
            for (int j = 0; j < i; j++) {
                if (IDCode[i] == IDCode[j]) {
                    throw new IllegalArgumentException("ID duplicato");
                }
            }
            code[i] = new Coda<>(IDCode[i]);
        }
    }

    private Coda<?> trovaCoda(int ID) {
        for (Coda<? extends Processabile> coda : code) {
            if (coda.getID() == ID) return coda;
        }
        throw new IllegalArgumentException("ID elenco non valido");
    }

    public <T extends Processabile> boolean inserisciRichiesta(int ID, T richiesta, int posizione) {
        Coda<? super T> destinazione = trovaCoda(ID);
        return destinazione.aggiungiElemento(richiesta, posizione);
    }

    public void evadiRichiesta(int id, int posizione) {
        Coda<? extends Processabile> elenco = trovaCoda(id);
        elenco.rimuoviElemento(posizione);
    }

    public void prossimaInLavorazione(int id) {
        Coda<? extends Processabile> elenco = trovaCoda(id);
        elenco.primoElemento();
    }

    public int cercaRichiesta(int id, T richiesta) {
        Coda<? extends Processabile> elenco = trovaCoda(id);
        return elenco.cercaElemento(richiesta);
    }
}