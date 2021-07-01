package datastructures;

public class HashTable<K, V> {

    private static final double POROG = 0.9f;

    private Node<K, V>[] table;
    private int M;
    private int elementsNum;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = (Node<K, V>[]) new Node[17];
        M = 17;
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
        int i = computeIndex(key);
        return table[i] != null;
    }

    public V get(K key) {
        int i = computeIndex(key);
        if (table[i] == null) {
            return null;
        }

        Node<K, V> node = table[i];
        while (node != null) {
            if (key.equals(node.key)) {
                return node.value;
            }
            node = node.next;
        }

        return null;
    }

    public void put(K key, V val) {
        if ((double)elementsNum / M > POROG) {
            table = grow();
        }

        int i = computeIndex(key);
        while (i < 0) {
            i += M;
        }

        if (table[i] == null) {
            table[i] = new Node<>(key, val, null);
        } else {
            table[i] = new Node<>(key, val, table[i]);
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
    }

    private int computeIndex(K key) {
        int i = key.hashCode() % M;
        if (i < 0) {
            return i + M;
        }
        return i;
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

                int j = key.hashCode() % M;
                if (newTable[j] == null) {
                    newTable[j] = new Node<>(key, val, null);
                } else {
                    newTable[j] = new Node<>(key, val, newTable[j]);
                }
                currentNode = currentNode.next;
            }
        }

        return newTable;
    }
}
