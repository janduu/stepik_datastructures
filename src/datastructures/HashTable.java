package datastructures;

public class HashTable<K, V> {

    private static final double FILL_FACTOR = 0.9;

    private Node<K, V>[] table;
    private int M;
    private int elementsNum;


    @SuppressWarnings("unchecked")
    public HashTable() {
        this.M = 17;
        table = (Node<K, V>[]) new Node[M];
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public V get(K key) {
        Node<K, V> n = getNode(key);
        return n == null ? null : n.value;
    }

    public void put(K key, V val) {
        if ((double) elementsNum / M > FILL_FACTOR) {
            table = grow();
        }

        int i = computeIndex(key);

        if (table[i] == null) {
            table[i] = new Node<>(key, val, null);
        } else {
            Node<K, V> node = getNode(key);
            if (node == null) {
                table[i] = new Node<>(key, val, table[i]);
            } else {
                node.value = val;
            }
        }
        elementsNum++;
    }

    public void remove(K key) {
        int i = computeIndex(key);
        if (table[i] == null) {
            return;
        }

        Node<K, V> node = table[i];
        Node<K, V> prev = null;

        while (node != null) {
            if (key.equals(node.key)) {
                if (prev != null) {
                    prev.next = node.next;
                } else {
                    table[i] = node.next;
                }

                node.value = null;
                node.key = null;
                node = null;

            } else {
                prev = node;
                node = node.next;
            }
        }

        elementsNum--;
    }

    private int computeIndex(K key) {
        int i = key.hashCode() % M;
        if (i < 0) {
            return i + M;
        }
        return i;
    }

    private Node<K, V> getNode(K key) {
        int i = computeIndex(key);
        if (table[i] == null) {
            return null;
        }

        Node<K, V> node = table[i];
        while (node != null) {
            if (key.equals(node.key)) {
                return node;
            }
            node = node.next;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private Node<K, V>[] grow() {
        M = M * 2;
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[M];

        for (Node<K, V> kvNode : table) {
            Node<K, V> currentNode = kvNode;

            while (currentNode != null) {
                K key = currentNode.key;
                V val = currentNode.value;

                int i = computeIndex(key);
                if (newTable[i] == null) {
                    newTable[i] = new Node<>(key, val, null);
                } else {
                    newTable[i] = new Node<>(key, val, newTable[i]);
                }
                currentNode = currentNode.next;
            }
        }

        return newTable;
    }
}
