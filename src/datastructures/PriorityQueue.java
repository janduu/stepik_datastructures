package datastructures;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Max Heap realization
 * @param <V>
 */
public class PriorityQueue <V> {

    private Object[] queue;
    private final Comparator<? super V> comparator;
    private int size = 0;
    public List<String> swapLogs = new LinkedList<>();

    public PriorityQueue(V[] elements, Comparator<? super V> comparator) {
        this.queue = elements;
        this.comparator = comparator;
        size = elements.length;
        buildHeap();
    }

    public PriorityQueue(Comparator<V> comparator) {
        this.queue = new Object[10];
        this.comparator = comparator;
    }

    public void add(V element) {
        if (size >= queue.length) {
            queue = grow();
        }
        queue[size] = element;
        siftUp(size);
        size++;
    }

    @SuppressWarnings("unchecked")
    public void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (comparator.compare((V) queue[parent], (V) queue[i]) < 0) {
                swap(parent, i);
                i = parent;
            } else {
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void siftDown(int i) {
        while (i < size) {
            int left = i * 2 + 1;
            int right = left + 1;

            if (left < size) {
                int maxChildIndex = left;

                if (right < size && comparator.compare((V) queue[left], (V) queue[right]) < 0) {
                    maxChildIndex = right;
                }

                if (comparator.compare((V) queue[i],(V) queue[maxChildIndex]) < 0) {
                    swap(i, maxChildIndex);
                    i = maxChildIndex;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
    }

    private Object[] grow() {
        int newLength = queue.length + (queue.length < 64 ? queue.length + 2 : (queue.length >> 1));
        Object[] newArr = new Object[newLength];
        System.arraycopy(queue, 0, newArr, 0, queue.length);
        return newArr;
    }

    private void buildHeap() {
        for (int i = queue.length / 2; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void swap(int a, int b) {
        swapLogs.add(a + " " + b);
        Object temp = queue[a];
        queue[a] = queue[b];
        queue[b] = temp;
    }

    @SuppressWarnings("unchecked")
    public V poll() {
        Object head = queue[0];

        size--;
        queue[0] = queue[size];
        queue[size] = null;

        siftDown(0);
        return (V) head;
    }
}
