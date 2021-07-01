package datastructures;

public class Stack <T> {
    private Node<T> top;
    private int size = 0;

    private static class Node<V> {
        V val;
        Node<V> next;

        public Node(V val, Node<V> next) {
            this.val = val;
            this.next = next;
        }
    }

    public void push(T val) {
        top = new Node<>(val, top);
        size++;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return top.val;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T pop = top.val;
        top = top.next;
        size--;
        return pop;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
