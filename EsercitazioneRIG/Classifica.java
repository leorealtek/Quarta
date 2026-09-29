package EsercitazioneRIG;

public class Classifica<T extends Classificabile> {

    private Classificabile[] top = new Classificabile[5];

    public void stampaClassifica() {
        for (int i = 0; i < top.length; i++) {
            if (top[i] != null) {
                System.out.println((i + 1) + ". " + top[i].nomeClassifica());
            }
        }
    }
    
    public boolean inserisciNuovoElemento(T elemento, int posizione) {
        if (posizione < 0 || posizione >= top.length) return false;

        boolean rimosso = top[top.length - 1] != null;

        for (int i = top.length - 1; i > posizione; i--) {
            top[i] = top[i - 1];
        }

        top[posizione] = elemento;

        return rimosso;
    }

    public void rimuoviElemento(int posizione) {
        if(posizione >= top.length || posizione < 0) throw new IllegalArgumentException("Posizione non valida");
        for (int i = posizione; i < top.length - 1; i++) {
            top[i] = top[i + 1];
        }
        top[top.length - 1] = null;
    }

    public void primoElemento() {
        for (Classificabile elemento : top) {
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
        if (index >= top.length) return -1;
        

        if (top[index] != null && top[index].equals(elemento)) return index + 1;
        
        return cercaElemento(elemento, index + 1);
    }
}