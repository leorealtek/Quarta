package Lists;

import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
    private class Iteratore implements Iterator<T> {

        private Queue<T> q;

        public Iteratore(Queue<T> q) {
            this.q = q;
        }

        @Override
        public boolean hasNext() {
            return !q.isEmpty();
        }

        @Override
        public T next() {
            return q.dequeue();
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new Iteratore(this);
    }

    private LinkedList<T> coda;

    public Queue() {
        this.coda = new LinkedList<>();
    }

    public void enqueue(T element) {
        coda.add(element);
    }

    public T dequeue() {
        if (this.isEmpty()) return null;
        T temp = coda.get(0);
        coda.remove(0);
        return temp;
    }

    public boolean isEmpty() {
        return coda.isEmpty();
    }
    
}