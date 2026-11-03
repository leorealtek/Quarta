package Lists;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class LinkedList<T> implements Iterable<T> {
    protected class Nodo {

        private final T dato;
        private Nodo succ;
        private Nodo prec;
         
        public Nodo(T dato) {
            this.prec = null;
            this.dato = dato;
            this.succ = null;
        }

        public Nodo(Nodo prec, T dato) {
            this.prec = prec;
            this.dato = dato;
            this.succ = null;
        }

        public Nodo(T dato, Nodo succ) {
            this.prec = null;
            this.dato = dato;
            this.succ = succ;
        }

        public Nodo(Nodo prec, T dato, Nodo succ) {
            this.prec = prec;
            this.dato = dato;
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

        public Nodo getPrec() {
            return prec;
        }

        public void setPrec(Nodo prec) {
            this.prec = prec;
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
    protected Nodo coda;
    protected int length;

    public LinkedList() {
        this.testa = null;
        this.coda = null;
        this.length = 0;
    }

    private int getPuntoMedio() {
        return (length + 1) / 2;
    }


    public T get(int p) {
        if (p < 0 || p >= length) {
            throw new IndexOutOfBoundsException("Posizione " + p + " fuori dai limiti");
        }
        else {
            Nodo scan;
            if (p < length / 2) {
                scan = this.testa;
                for (int i = 0; i < p; i++) {
                    scan = scan.getSucc();
                }
            }
            else {
                scan = this.coda;
                for (int i = length - 1; i > p; i--) {
                    scan = scan.getPrec();
                }
            }
            return scan.getDato();
        }
    }

    public void insert(T elemento, int p) {
        if (p < 0 || p > length) {
            throw new IndexOutOfBoundsException("Posizione " + p + " fuori dai limiti");
        }

        else if (length == 0) {
            this.testa = new Nodo(elemento);
            this.coda = testa;
        }

        else if (p == 0) {
            Nodo nuovo = new Nodo(elemento, testa);
            this.testa.setPrec(nuovo);
            this.testa = nuovo;
        }

        else if (p == length) {
            Nodo nuovo = new Nodo(coda, elemento);
            this.coda.setSucc(nuovo);
            this.coda = nuovo;
        }

        else {
            Nodo scan;
            if (p < length / 2) {
                scan = this.testa;
                for (int i = 0; i < p - 1; i++) {
                    scan = scan.getSucc();
                }
                Nodo nuovo = new Nodo(scan, elemento, scan.getSucc());
                scan.setSucc(nuovo);
                nuovo.getSucc().setPrec(nuovo);
            }
            else {
                scan = this.coda;
                for (int i = length - 1; p < i; i--) {
                    scan = scan.getPrec();
                }
                Nodo nuovo = new Nodo(scan.getPrec(), elemento, scan);
                scan.setPrec(nuovo);
                nuovo.getPrec().setSucc(nuovo);
            }
        }
        this.length++;
    }

    public void add(T element) {
        this.insert(element, length);
    }

    public void remove(int p) {
        if (p < 0 || p >= length) {
            throw new IndexOutOfBoundsException("Posizione " + p + " fuori dai limiti");
        }

        else if (length == 1) {
            this.testa = null;
            this.coda = null;
        }

        else if (length == 2) {
            if (p == 0) {
                this.coda.setPrec(null);
                this.testa = coda;
            }
            else {
                this.testa.setSucc(null);
                this.coda = testa;
            }
        }

        else if (p == 0) {
            this.testa = this.testa.getSucc();
            this.testa.setPrec(null);
        }
        
        else if (p == length - 1) {
            this.coda = this.coda.getPrec();
            this.coda.setSucc(null);
        }

        else {
            Nodo scan;
            if (p < length / 2) {
                scan = this.testa;
                for (int i = 0; i < p; i++) {
                    scan = scan.getSucc();
                }
            }
            else {
                scan = this.coda;
                for (int i = length - 1; p < i; i--) {
                    scan = scan.getPrec();
                }
            }
            scan.getPrec().setSucc(scan.getSucc());
            scan.getSucc().setPrec(scan.getPrec());
        }
        this.length--;
    }

    public void remove(T element) {
        Nodo scanTesta = testa;
        Nodo scanCoda = coda;
        int index = 0;
        boolean find = false;

        for (int i = 0; i < getPuntoMedio(); i++) {
            if (scanTesta.getDato().equals(element)) {
                index = i;
                find = true;
                break;
            }
            else if (scanCoda.getDato().equals(element)) {
                index = length - i - 1;
                find = true;
                break;
            }
            scanTesta = scanTesta.getSucc();
            scanCoda = scanCoda.getPrec();
        }

        if (!find) throw new IllegalArgumentException("L' elemento " + element + " non è nella lista");

        if (length == 1) {
            this.testa = null;
            this.coda = null;
        }

        else if (length == 2) {
            if (index == 0) {
                this.coda.setPrec(null);
                this.testa = coda;
            }
            else {
                this.testa.setSucc(null);
                this.coda = testa;
            }
        }

        else if (index == 0) {
            this.testa = this.testa.getSucc();
            this.testa.setPrec(null);
        }
        
        else if (index == length - 1) {
            this.coda = this.coda.getPrec();
            this.coda.setSucc(null);
        }

        else {
            Nodo scan = (index < getPuntoMedio()) ? scanTesta : scanCoda;
            scan.getPrec().setSucc(scan.getSucc());
            scan.getSucc().setPrec(scan.getPrec());
        }
    }

    public boolean contains(T element) {
        Nodo scanTesta = testa;
        Nodo scanCoda = coda;
        for (int i = 0; i < getPuntoMedio(); i++) {
            if (scanTesta.getDato().equals(element) || scanCoda.getDato().equals(element)) return true;
            scanTesta = scanTesta.getSucc();
            scanCoda = scanCoda.getPrec();
        }
        return false;
    }

    public int indexOf(T element) {
        Nodo scanTesta = testa;
        Nodo scanCoda = coda;
        for (int i = 0; i < getPuntoMedio(); i++) {
            if (scanTesta.getDato().equals(element)) return i;
            if (scanCoda.getDato().equals(element)) return length - i - 1;
            scanTesta = scanTesta.getSucc();
            scanCoda = scanCoda.getPrec();
        }
        return -1;
    }

    public void swapConsecutive(int pos1) {
        if (pos1 < 0 || pos1 >= length) {
            throw new IndexOutOfBoundsException("Posizione " + pos1 + " fuori dai limiti");
        }

        if (pos1 + 1 == length) {
            return;
        }
        
        Nodo scan1;

        if (pos1 < getPuntoMedio()) {
            scan1 = testa;
            for (int i = 0; i < pos1; i++) {
                scan1 = scan1.getSucc();
            }
        }
        else {
            scan1 = coda;
            for (int i = length - 1; i > pos1; i--) {
                scan1 = scan1.getPrec();
            }
        }

        Nodo scan2 = scan1.getSucc();

        if (scan1 == testa) {
            testa = scan2;
        }
        else {
            scan1.getPrec().setSucc(scan2);
        }

        if (scan2 == coda) {
            coda = scan1;
        }
        else {
            scan1.getPrec().setSucc(scan2);
        }

        scan1.setSucc(scan2.getSucc());
        scan2.setSucc(scan1);
        scan2.setPrec(scan1.getPrec());
        scan1.setPrec(scan2);
    }

    public <E> LinkedList<E> map(Function<T, E> f) {
        LinkedList<E> newList = new LinkedList<>();
        forEach(e -> newList.add(f.apply(e)));
        return newList;
    }

    public void print() {
        this.forEach(t -> System.out.print(t + " ") );
        System.out.println();
    }

    public void printLine() {
        this.forEach(t -> System.out.println(t + " ") );
    }

    public int length() {
        return length;
    }

    public static void main(String[] args) {
        LinkedList<Integer> lista = new LinkedList<>();

        lista.add(2);
        lista.add(3);
        lista.add(244);
        lista.add(24);
        lista.add(2444);

        lista.swapConsecutive(2);
        lista.print();
    }
}
