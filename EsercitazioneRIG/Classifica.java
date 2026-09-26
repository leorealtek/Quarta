package EsercitazioneRIG;

public class Classifica<T extends PassioneGenerica & Classificabile> {

    private PassioneGenerica[] top = new PassioneGenerica[5];

    public void stampaClassifica() {
        for (int i = 0; i < top.length; i++) {
            if (top[i] != null) {
                System.out.println((i + 1) + ". " + ((Classificabile) top[i]).nomeClassifica());
            }
        }
    }
    
    public boolean inserisciNuovoElemento(T elemento, int posizione) {
        if (posizione < 0 || posizione >= top.length)
            return false;

        boolean rimosso = top[top.length - 1] != null;

        for (int i = top.length - 1; i > posizione; i--) {
            top[i] = top[i - 1];
        }

        top[posizione] = elemento;

        return rimosso;
    }

    public void rimuoviElemento(int posizione) {
        if(posizione >= top.length || posizione < 0) return;
        for (int i = posizione; i < top.length - 1; i++) {
            top[i] = top[i + 1];
        }
        top[top.length - 1] = null;
    }

    public void primoElemento() {
        if (top[0] != null) System.out.println(top[0]);
    }

    public int cercaElemento(T elemento, int index) {
        if (index >= top.length || top[index] == null) return -1;
        if (top[index].equals(elemento)) return index + 1;
        else return cercaElemento(elemento, index + 1);
    }
}