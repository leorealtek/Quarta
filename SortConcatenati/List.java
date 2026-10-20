package SortConcatenati;

public class List<T> {
    protected class Nodo {
        private T dato;
        private Nodo succ;
         
        public Nodo(T dato) {
            this.dato = dato;
            this.succ = null;
        }

        public Nodo(T data, Nodo succ) {
            this.dato = data;
            this.succ = succ;
        }

        public T getDato() {
            return this.dato;
        }

        public Nodo getSucc() {
            return this.succ;
        }

        public void setSucc(Nodo succ) {
            this.succ = succ;
        }
    }

    protected Nodo testa;

    public List() {
        this.testa = null;
    }

    public int length() {
        Nodo scan = this.testa;
        int i = 0;
        while (scan != null) {
            i++;
            scan = scan.getSucc();
        }
        return i;
    }
     
    public T get(int p) {
        int l = this.length();
        if (p < 0 || p >= l) {
            throw new IndexOutOfBoundsException("Posizione " + p + " fuori dai limiti");
        }

        else {
            Nodo scan = this.testa;
            for (int i = 0; i < p; i++) {
                scan = scan.getSucc();
            }

            return scan.getDato();
        }
    }

    public void insert(T elemento, int p) {
        int l = this.length();
        if (p < 0 || p > l) {
            throw new IndexOutOfBoundsException("Posizione " + p + " fuori dai limiti");
        }
        else if (p == 0) {
            this.testa = new Nodo(elemento, this.testa);
        }
        else {
            Nodo scan = this.testa;
            for (int i = 0; i < p - 1; i++) {
                scan = scan.getSucc();
            }
            Nodo nuovo = new Nodo(elemento, scan.getSucc());
            scan.setSucc(nuovo);
        }
    }

    public void add(T element) {
        this.insert(element, this.length());
    }

    public void remove(int p) {
        int l = this.length();
        if (p < 0 || p >= l) {
            throw new IndexOutOfBoundsException("Posizione " + p + " fuori dai limiti");
        }
        else if (p == 0) {
            this.testa = this.testa.getSucc();
        }
        else {
            Nodo scan = this.testa;
            for(int i = 0; i < p - 1; i++) {
                scan = scan.getSucc();
            }
            scan.setSucc(scan.getSucc().getSucc());
        }
    }
}