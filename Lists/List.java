package Lists;

import java.util.Iterator;

public class List<T> implements Iterable<T> {
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
    private class Iteratore implements Iterator<T> {

        private Nodo cursore;

        public Iteratore() {
            cursore = testa;
        }

        @Override
        public boolean hasNext() {
            return cursore != null;
        }

        @Override
        public T next() {
            T temp = cursore.getDato();
            cursore = cursore.getSucc();
            return temp;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new Iteratore();
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

    protected void swap(int pos1, int pos2) {
        Nodo temp1 = null, temp2 = null, temp3 = testa;
        for (int i = 0; i < pos2; i++) {
            temp1 = temp2;
            temp2 = temp3;
            temp3 = temp3.getSucc();
        }
        if (temp1 == null) {
            temp2.setSucc(temp3.getSucc());
            temp3.setSucc(temp2);
            testa = temp3;
        }
        else {
            temp1.setSucc(temp3);
            temp2.setSucc(temp3.getSucc());
            temp3.setSucc(temp2);
        }
    }

    protected void swapp(int pos1, int pos2) {
        
    }

    public void stampa() {
        this.forEach(t -> System.out.println(t + " ") );
        System.out.println("");
    }
}