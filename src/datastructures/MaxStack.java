package datastructures;

public class MaxStack<V extends Comparable<V>> {
    private final Stack<V> elements = new Stack<>();
    private final Stack<V> max = new Stack<>();

    public V peek() {
        return elements.peek();
    }

    public V pop() {
        max.pop();
        return elements.pop();
    }

    public void push(V value) {
        elements.push(value);

        if (max.isEmpty() || max.peek().compareTo(value) < 0) {
            max.push(value);
        } else {
            max.push(max.peek());
        }
    }

    public V max() {
        return max.peek();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}
