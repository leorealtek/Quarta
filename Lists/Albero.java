package Lists;

public class Albero<T> {
    private class NodoAlbero {
        private final T dato;
        private List<NodoAlbero> figli;
        
        public NodoAlbero(T dato) {
            this.dato = dato;
            this.figli = new List<>();
        }

        public T getDato() {
            return dato;
        }

        public List<NodoAlbero> getFigli() {
            return figli;
        }

        public void setFigli(List<NodoAlbero> figli) {
            this.figli = figli;
        }

        public void aggiungiFiglio(NodoAlbero n){
            figli.add(n);
        }
    }
    
    private NodoAlbero radice;

    public void stampaAlbero(){ 
        stampaRic(this.radice);
    }

    private void stampaRic(NodoAlbero n){
        if (n == null) return;
        System.out.println(n.getDato());
        n.getFigli().forEach(f -> stampaRic(f));
    }

    public List<T> esportaAlberoDFS() {
        List<T> risultato = new List<>();
        esportazioneDFS(risultato, this.radice);
        return risultato;
    }

    private void esportazioneDFS(List<T> heap, NodoAlbero n) {
        if (n == null) return;
        heap.add(n.getDato());
        n.getFigli().forEach(f -> esportazioneDFS(heap, f));
    }

    private NodoAlbero cercaNodoDFS(T dato){
        if (dato == null) throw new IllegalArgumentException();
        if (this.radice == null) return null;
        return cercaNodoDFSAux(dato, this.radice);
    }

    private NodoAlbero cercaNodoDFSAux(T dato, NodoAlbero r){
        if (r == null) return null;
        if (r.getDato().equals(dato)) return r;
        NodoAlbero risultato = null;
        for (NodoAlbero n : r.getFigli()) {
            risultato = cercaNodoDFSAux(dato, n);
            if (risultato != null) break;
        }
        return risultato;
    }

    public boolean inserisciDFS(T nuovo, T padre){
        if (nuovo == null) throw new IllegalArgumentException();
        if (this.radice == null){
            this.radice = new NodoAlbero(nuovo);
            return true;
        }
        if (padre == null){
            NodoAlbero salva = this.radice;
            this.radice = new NodoAlbero(nuovo);
            this.radice.aggiungiFiglio(salva);
            return true;
        }
        NodoAlbero trova = cercaNodoDFS(padre);
        if (trova != null){
            trova.aggiungiFiglio(new NodoAlbero(nuovo));
            return true;
        }
        return false;
    }

    public boolean cercaDFS(T dato){
        return cercaNodoDFS(dato) != null;
    }
}