package EsercitazioneMulti;

public class Coda<T extends Processabile> {
    private Processabile[] coda = new Processabile[5];
    private final int ID;
    private final Class<T> tipo;

    public Coda(int ID, Class<T> tipo) {
        this.ID = ID;
        this.tipo = tipo;
    }

    public boolean aggiungiElemento(T elemento, int posizione) {
        if (elemento == null) {
            throw new IllegalArgumentException("La richiesta non può essere null.");
        }

        boolean codaGenerica = tipo.equals(Processabile.class);
        boolean richiestaGenerica = elemento.getClass().equals(Generico.class);

        if (!codaGenerica && !richiestaGenerica && !tipo.isInstance(elemento)) {
            throw new IllegalArgumentException(
                "La richiesta non è compatibile con questa coda."
            );
        }

        int numeroElementi = 0;
        for (Processabile elementoCoda : coda) {
            if (elementoCoda != null) numeroElementi++;
        }

        int indiceEffettivo = Math.min(posizione - 1, numeroElementi);
        boolean rimosso = coda[coda.length - 1] != null;

        for (int i = coda.length - 1; i > indiceEffettivo; i--) {
            coda[i] = coda[i - 1];
        }

        coda[indiceEffettivo] = elemento;
        return rimosso;
    }

    public void rimuoviElemento(int posizione) {
        if (posizione < 1 || posizione > coda.length) {
            throw new IllegalArgumentException("Posizione non valida");
        }

        for (int i = posizione - 1; i < coda.length - 1; i++) {
            coda[i] = coda[i + 1];
        }
        coda[coda.length - 1] = null;
    }

    public void primoElemento() {
        if (coda[0] != null) System.out.println(coda[0]);
    }

    public int cercaElemento(Processabile richiesta) {
        return cercaElemento(richiesta, 0);
    }

    private int cercaElemento(Processabile richiesta, int indice) {
        if (indice == coda.length) return -1;

        if (coda[indice] != null && coda[indice].equals(richiesta)) {
            return indice + 1;
        }

        return cercaElemento(richiesta, indice + 1);
    }

    public void stampaCoda() {
        for (Processabile elemento : coda) {
            if (elemento != null) System.out.println(elemento);
        }
    }

    public int getID() {
        return ID;
    }
}