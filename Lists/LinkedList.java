package Lists;

import java.util.Iterator;
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

        @Override
        public String toString() {
            return "Dato: " + dato;
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

    protected int getPuntoMedio() {
        return (length + 1) / 2;
    }


    public T get(int position) {
        if (position < 0 || position >= length) {
            throw new IndexOutOfBoundsException("Posizione " + position + " fuori dai limiti");
        }
        else {
            Nodo scan;
            if (position < getPuntoMedio()) {
                scan = this.testa;
                for (int i = 0; i < position; i++) {
                    scan = scan.getSucc();
                }
            }
            else {
                scan = this.coda;
                for (int i = length - 1; i > position; i--) {
                    scan = scan.getPrec();
                }
            }
            return scan.getDato();
        }
    }

    public void insert(T elemento, int position) {
        if (position < 0 || position > length) {
            throw new IndexOutOfBoundsException("Posizione " + position + " fuori dai limiti");
        }

        else if (length == 0) {
            this.testa = new Nodo(elemento);
            this.coda = testa;
        }

        else if (position == 0) {
            Nodo nuovo = new Nodo(elemento, testa);
            this.testa.setPrec(nuovo);
            this.testa = nuovo;
        }

        else if (position == length) {
            Nodo nuovo = new Nodo(coda, elemento);
            this.coda.setSucc(nuovo);
            this.coda = nuovo;
        }

        else {
            Nodo scan;
            if (position < getPuntoMedio()) {
                scan = this.testa;
                for (int i = 0; i < position - 1; i++) {
                    scan = scan.getSucc();
                }
                Nodo nuovo = new Nodo(scan, elemento, scan.getSucc());
                scan.setSucc(nuovo);
                nuovo.getSucc().setPrec(nuovo);
            }
            else {
                scan = this.coda;
                for (int i = length - 1; position < i; i--) {
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

    public void remove(int position) {
        if (position < 0 || position >= length) {
            throw new IndexOutOfBoundsException("Posizione " + position + " fuori dai limiti");
        }

        else if (length == 1) {
            this.testa = null;
            this.coda = null;
        }

        else if (length == 2) {
            if (position == 0) {
                this.coda.setPrec(null);
                this.testa = coda;
            }
            else {
                this.testa.setSucc(null);
                this.coda = testa;
            }
        }

        else if (position == 0) {
            this.testa = this.testa.getSucc();
            this.testa.setPrec(null);
        }
        
        else if (position == length - 1) {
            this.coda = this.coda.getPrec();
            this.coda.setSucc(null);
        }

        else {
            Nodo scan;
            if (position < getPuntoMedio()) {
                scan = this.testa;
                for (int i = 0; i < position; i++) {
                    scan = scan.getSucc();
                }
            }
            else {
                scan = this.coda;
                for (int i = length - 1; position < i; i--) {
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
        this.length--;
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

    public void swap(int pos1, int pos2) {
        if (pos1 < 0 || pos1 >= length) {
            throw new IndexOutOfBoundsException("Posizione " + pos1 + " fuori dai limiti");
        }

        if (pos2 < 0 || pos2 >= length) {
            throw new IndexOutOfBoundsException("Posizione " + pos2 + " fuori dai limiti");
        }

        if (pos1 > pos2) {
            throw new IllegalArgumentException("La prima posizione deve essere minore della seconda");
        }

        if (pos1 == pos2) return;

        Nodo scanTesta, scanCoda;

        if (pos1 < getPuntoMedio()) {
            scanTesta = testa;


            for (int i = 0; i < pos1; i++) {
                scanTesta = scanTesta.getSucc();
            }
        }

        else {
            scanTesta = coda;

            for (int i = length - 1; pos1 < i; i--) {
                scanTesta = scanTesta.getPrec();
            }
        }
        
        if (pos2 < getPuntoMedio()) {
            scanCoda = testa;

            for (int i = 0; i < pos2; i++) {
                scanCoda = scanCoda.getSucc();
            }
        }

        else {
            scanCoda = coda;

            for (int i = length - 1; pos2 < i; i--) {
                scanCoda = scanCoda.getPrec();
            }
        }

        if (scanTesta == testa) {
            Nodo temp = scanTesta.getSucc();
            scanTesta.getSucc().setPrec(scanCoda);
            scanCoda.getPrec().setSucc(scanTesta);
            scanCoda.getSucc().setPrec(scanTesta);

            scanTesta.setPrec(scanCoda.getPrec());
            scanCoda.setPrec(null);

            scanTesta.setSucc(scanCoda.getSucc());
            scanCoda.setSucc(temp);
            testa = scanCoda;
        }

        else if (scanCoda == coda) {
            Nodo temp = scanCoda.getPrec();
            scanCoda.getPrec().setSucc(scanTesta);
            scanTesta.getPrec().setSucc(scanCoda);
            scanTesta.getSucc().setPrec(scanCoda);

            scanCoda.setSucc(scanTesta.getSucc());
            scanTesta.setSucc(null);

            scanCoda.setPrec(scanTesta.getPrec());
            scanTesta.setPrec(temp);

            coda = scanTesta;
        }

        else {
            Nodo temp = scanTesta;
            scanTesta.getPrec().setSucc(scanCoda);
            scanTesta.getSucc().setPrec(scanCoda);

            scanCoda.getPrec().setSucc(scanTesta);
            scanCoda.getSucc().setPrec(scanTesta);

            scanTesta.setPrec(scanCoda.getPrec());
            scanTesta.setSucc(scanCoda.getSucc());
            
            scanCoda.setPrec(temp.getPrec());
            scanCoda.setSucc(temp.getSucc());
        }
        
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public <E> LinkedList<E> map(Function<T, E> f) {
        LinkedList<E> newList = new LinkedList<>();
        this.forEach(e -> newList.add(f.apply(e)));
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

        lista.add(56);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        lista.add(6);
        System.out.println(lista.indexOf(2));
    }

}