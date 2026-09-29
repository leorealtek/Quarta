package EsercitazioneMulti;

public class Coda<T extends Processabile> {
    private Processabile[] coda = new Processabile[5];

    public boolean inserisciNuovoElemento(T elemento, int posizione) {
        if (posizione < 0 || posizione >= coda.length) return false;

        boolean rimosso = coda[coda.length - 1] != null;

        for (int i = coda.length - 1; i > posizione; i--) {
            coda[i] = coda[i - 1];
        }

        coda[posizione] = elemento;

        return rimosso;
    }

    public void rimuoviElemento(int posizione) {
        if(posizione >= coda.length || posizione < 0) throw new IllegalArgumentException("Posizione non valida");
        for (int i = posizione; i < coda.length - 1; i++) {
            coda[i] = coda[i + 1];
        }
        coda[coda.length - 1] = null;
    }

    public void primoElemento() {
        for (Processabile elemento : coda) {
            if (elemento != null) {
                System.out.println("Primo elemento: " + elemento);
                return;
            }
        }
    }

    public int cercaElemento(T elemento) {
        return cercaElemento(elemento, 0);
    }

    private int cercaElemento(T elemento, int index) {
        if (index >= coda.length) return -1;
        

        if (coda[index] != null && coda[index].equals(elemento)) return index + 1;
        
        return cercaElemento(elemento, index + 1);
    }
}