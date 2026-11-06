package Lists;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    private class Iteratore implements Iterator<T> {

        private Stack<T> s;

        public Iteratore(Stack<T> s) {
            this.s = s;
        }

        @Override
        public boolean hasNext() {
            return s.peek() != null;
        }

        @Override
        public T next() {
            return s.pop();
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new Iteratore(this);
    }

    private LinkedList<T> stack;

    public Stack() {
        this.stack = new LinkedList<>();
    }

    public void push(T element) {
        stack.insert(element, 0);
    }

    public T pop() {
        T temp = this.peek();
        if (temp != null) stack.remove(temp);
        return temp;
    }

    public T peek() {
        return (stack.length() != 0) ? stack.get(0) : null;
    }

}