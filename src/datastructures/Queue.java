package datastructures;

public class Queue<V> {

    private Node<V> head;
    private Node<V> tail;

    private int size;

    private static class Node<T> {
        T val;
        Node<T> next;

        public Node(T val) {
            this.val = val;
        }
    }

    public void add(V val) {
        if (head == null) {
            head = tail = new Node<>(val);
        } else {
            tail.next = new Node<>(val);
            tail = tail.next;
        }
        size++;
    }

    public V peek() {
        return head.val;
    }

    public V poll() {
        if (size == 0) {
            return null;
        } else {
            V value = head.val;
            head = head.next;
            size--;
            return value;
        }
    }

    public int size() {
        return this.size;
    }
}
