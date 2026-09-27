package EsercitazioneRIG;

public class Gestore {

    private Classifica<?>[] classifiche;
    private int numeroClassifiche = 0;

    public Gestore(int capacita) {
        classifiche = new Classifica<?>[capacita];
    }

    public void aggiungiClassifica(Classifica<? extends PassioneGenerica> classifica) {
        if (classifica == null) {
            throw new IllegalArgumentException("Classifica nulla");
        }

        for (int i = 0; i < numeroClassifiche; i++) {
            if (classifiche[i] == classifica) {
                return;
            }
        }

        if (numeroClassifiche == classifiche.length) {
            throw new IllegalArgumentException("Gestore pieno");
        }

        classifiche[numeroClassifiche++] = classifica;
    }

    public <E extends PassioneGenerica> boolean inserisciNuovoElemento(Classifica<? super E> classifica, E elemento, int posizione) {
        verificaClassifica(classifica);
        return classifica.inserisciNuovoElemento(elemento, posizione);
    }

    public <E extends PassioneGenerica> int cercaElemento(Classifica<? super E> classifica, E elemento) {
        verificaClassifica(classifica);
        return classifica.cercaElemento(elemento);
    }

    public void primoElemento(Classifica<? extends PassioneGenerica> classifica) {
        verificaClassifica(classifica);
        classifica.primoElemento();
    }

    public void rimuoviElemento(Classifica<?> classifica, int indice) {
        verificaClassifica(classifica);
        classifica.rimuoviElemento(indice);
    }

    public void stampaClassifiche() {
        for (int i = 0; i < numeroClassifiche; i++) {
            classifiche[i].stampaClassifica();
        }
    }

    private void verificaClassifica(Classifica<?> classifica) {
        for (int i = 0; i < numeroClassifiche; i++) {
            if (classifiche[i] == classifica) {
                return;
            }
        }

        throw new IllegalArgumentException("Classifica non registrata");
    }
}