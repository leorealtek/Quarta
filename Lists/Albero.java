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
}