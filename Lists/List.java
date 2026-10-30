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

    public void remove(T element) {
        remove(indexOf(element));
    }

    public boolean contains(T element) {
        for (T e : this) {
            if (e == element) return true;
        }
        return false;
    }

    public int indexOf(T element) {
        int count = 0;
        for (T e : this) {
            if (e.equals(element)) return count;
            else count++;
        }
        return -1;
    }

    public void swapOnlyConsecutive(int pos1, int pos2) {
        if (pos2 - 1 != pos1) throw new IllegalArgumentException("Le due posizioni non sono consecutive");

        int length = length();

        if (pos1 < 0 || pos1 >= length) {
            throw new IndexOutOfBoundsException("Posizione " + pos1 + " fuori dai limiti");
        }

        if (pos2 < 0 || pos2 >= length) {
            throw new IndexOutOfBoundsException("Posizione " + pos2 + " fuori dai limiti");
        }

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

    public void swap(int pos1, int pos2) {
        if (pos1 > pos2) {
            throw new IllegalArgumentException("La prima posizione deve essere minore della seconda");
        }

        if (pos1 == pos2) return;

        int length = length();

        if (pos1 < 0 || pos1 >= length) {
            throw new IndexOutOfBoundsException("Posizione " + pos1 + " fuori dai limiti");
        }

        if (pos2 < 0 || pos2 >= length) {
            throw new IndexOutOfBoundsException("Posizione " + pos2 + " fuori dai limiti");
        }

        Nodo temp1 = null, temp2 = testa;
        Nodo temp3 = null, temp4 = testa;

        for (int i = 0; i < pos1; i++) {
            temp1 = temp2;
            temp2 = temp2.getSucc();
        }

        for (int i = 0; i < pos2; i++) {
            temp3 = temp4;
            temp4 = temp4.getSucc();
        }

        if (temp1 == null) {
            testa = temp4;
        } else {
            temp1.setSucc(temp4);
        }

        if (temp3 == temp2) {
            temp2.setSucc(temp4.getSucc());
            temp4.setSucc(temp2);
        } else {
            Nodo temp5 = temp2.getSucc();

            temp2.setSucc(temp4.getSucc());
            temp4.setSucc(temp5);
            temp3.setSucc(temp2);
        }
    }

    public void print() {
        this.forEach(t -> System.out.print(t + " ") );
        System.out.println("");
    }

    public void printLine() {
        this.forEach(t -> System.out.println(t + " ") );
        System.out.println("");
    }
}