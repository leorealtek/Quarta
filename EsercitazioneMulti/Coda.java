package EsercitazioneMulti;

public class Coda<T extends Processabile> {
    private Processabile[] coda = new Processabile[5];
    private final int ID;

    public Coda(int ID) {
        this.ID = ID;
    }

    public boolean aggiungiElemento(T elemento, int posizione) {
        if (posizione < 1 || posizione > coda.length) {
            throw new IllegalArgumentException("Posizione non valida");
        }

        int indice = posizione - 1;

        int numeroElementi = 0;
        for (Processabile e : coda) {
            if (e != null) numeroElementi++;
        }

        int indiceEffettivo = Math.min(indice, numeroElementi);
        boolean rimosso = coda[coda.length - 1] != null;

        for (int i = coda.length - 1; i > indiceEffettivo; i--) {
            coda[i] = coda[i - 1];
        }

        coda[indiceEffettivo] = elemento;
        return rimosso;
    }

    public void rimuoviElemento(int posizione) {
        posizione--; 
        if(posizione >= coda.length || posizione < 0) throw new IllegalArgumentException("Posizione non valida");
        for (int i = posizione; i < coda.length - 1; i++) {
            coda[i] = coda[i + 1];
        }
        coda[coda.length - 1] = null;
    }

    public void primoElemento() {
        if (coda[0] != null) System.out.println("Primo elemento: " + coda[0]);
    }

    public int cercaElemento(T elemento) {
        return cercaElemento(elemento, 0);
    }

    private int cercaElemento(T elemento, int index) {
        if (index >= coda.length) return -1;
        

        if (coda[index] != null && coda[index].equals(elemento)) return index + 1;
        
        return cercaElemento(elemento, index + 1);
    }

    public int getID() {
        return ID;
    }

}