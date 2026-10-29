package Lists;

public class SortedList<T extends Comparable<T>> extends List<T> {
    private boolean isSorted;

    public SortedList() {
        super();
        this.isSorted = true;
    }

    public void addSorted(T element) {
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

    @Override
    public void swapOnlyConsecutive(int pos1, int pos2) {
        isSorted = false;
        super.swapOnlyConsecutive(pos1, pos2);
    }

    @Override
    public void swap(int pos1, int pos2) {
        isSorted = false;
        super.swap(pos1, pos2);
    }

    public void sort() {
        if (isSorted) return;
        int length = length();
        boolean swapped;
        int count = 0;
        do {
            swapped = false;
            for (int i = 0; i < length - count - 1; i++) {
                if (get(i).compareTo(get(i + 1)) > 0) {
                    swapOnlyConsecutive(i, i + 1);
                    swapped = true;
                }
            }
            count++;
        } while (swapped);
        isSorted = true;
    }

    public static void main(String[] args) {
        SortedList<Integer> numeri = new SortedList<>();
        numeri.add(2);
        numeri.add(3);
        numeri.add(1);
        numeri.add(5);
        numeri.sort();
        numeri.print();
    }
}