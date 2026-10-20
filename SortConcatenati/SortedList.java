package SortConcatenati;

public class SortedList<T extends Comparable<T>> extends List<T> {
    private boolean isSorted;

    public SortedList() {
        super();
        this.isSorted = true;
    }

    public void insertSorted(T element) {
        if (!this.isSorted) sort();
        int l = this.length();
        if (l == 0) super.insert(element, 0);
        else {
            int index = 0;
            while (index < l && this.get(index).compareTo(element) < 0) {
                index++;
            }
            super.insert(element, index);
        }
    }

    @Override
    public void insert(T element, int i) {
        this.isSorted = false;
        super.insert(element, i);
    }

    @Override
    public void add(T element) {
        this.isSorted = false;
        super.add(element);
    }

    /*
    public void sort() {
        if (isSorted) return;
        SortedList<T> nuovaLista = new SortedList<>();
        for (int i = 0; i < this.length(); i++) {
            nuovaLista.insertSorted(this.get(0));
            this.remove(0);
        }
        this.testa = nuovaLista.testa;
        swapped = true;
    }
    */

    public void sort() {
        if (isSorted) return;
        int length = length();
        boolean swapped;
        int count = 0;
        do {
            swapped = false;
            for (int i = 0; i < length - count - 1; i++) {
                Nodo successivo = testa.getSucc();
                Nodo attuale = testa;
                if (attuale.getDato().compareTo(successivo.getDato()) > 0) {
                    swap(i, i + 1);
                    swapped = true;
                }
                attuale = successivo;
                successivo = successivo.getSucc();
            }
            count++;
        } while (swapped);
        isSorted = true;
    }

    public void swap(int pos1, int pos2) {
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
}