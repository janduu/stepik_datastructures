package datastructures;

import java.util.Objects;

public class MaxQueue <V extends Comparable<V>>{
    private final MaxStack<V> maxStack = new MaxStack<>();
    private final MaxStack<V> inverted = new MaxStack<>();

    public void add(V val) {
        maxStack.push(val);
    }

    public V peek() {
        fillInvertedIfEmpty();
        return inverted.peek();
    }

    public V poll() {
        fillInvertedIfEmpty();
        return inverted.pop();
    }

    public V max() {
        fillInvertedIfEmpty();
        V max1 = inverted.max();
        V max2 = maxStack.max();

        return max2 == null || max1.compareTo(max2) > 0 ? max1 : max2;
    }

    private void fillInvertedIfEmpty() {
        if (inverted.isEmpty()) {
            while (!maxStack.isEmpty()) {
                inverted.push(maxStack.pop());
            }
        }
    }
}
