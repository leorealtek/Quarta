package EsercitazioneRIG;

public class Classifica<T extends PassioneGenerica> {

    public PassioneGenerica[] top = new PassioneGenerica[5];

    public void stampaClassifica(Classifica<T> c) {
        for (PassioneGenerica passioneGenerica : c.top) {
            
        }
    }
    
    public boolean inserisciNuovoElemento(Classifica<T> c, T elemento, int posizione) {
        if(posizione > 4 || posizione < 0) return false;
        if(c.top[posizione] == null) { 
            c.top[posizione] = elemento;
            return false;
        }
        if (posizione != 4) {
            for (int i = c.top.length - 1; posizione < i; i--) {
            c.top[i] = c.top[i - 1];
            }
        }
        c.top[posizione] = elemento;
        return true;
    }

    public void rimuoviElemento(Classifica<T> c, int posizione) {
        if(posizione > 4 || posizione < 0) return;
        for (int i = posizione; i < c.top.length - 2; i++) {
            c.top[i + 1] = c.top[i];
        }
        c.top[c.top.length - 1] = null;
    }

    public void primoElemento(Classifica<T> c) {
        if (c.top[0] != null) System.out.println(c.top[0]);
    }

    public int cercaElemento(Classifica<T> c, T elemento, int index) {
        int posizione;
        if (index > 4) posizione = -1;
        if (c.top[index].getTitolo().equals(elemento.getTitolo())) posizione = index;
        else posizione = cercaElemento(c, elemento, index + 1);
        return posizione + 1;
    }
}