package solutions.module_3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChainingHashing {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();


        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        HashTable<StringWrapper, StringWrapper> hashTable = new HashTable<>(m);

        for (int i = 0; i < n; i++) {
            String[] strings = br.readLine().split("\\W+");
            String cmd = strings[0];
            StringWrapper param = new StringWrapper(strings[1]);

            if ("add".equals(cmd)) {
                hashTable.put(param, param);
            } else if ("find".equals(cmd)) {
                if (hashTable.contains(param)) {
                    output.append("yes\n");
                } else {
                    output.append("no\n");
                }
            } else if ("del".equals(cmd)) {
                hashTable.remove(param);
            } else {
                int index = Integer.parseInt(param.toString());
                HashTable.Node<StringWrapper, StringWrapper> node = hashTable.table[index];
                while (node != null) {
                    output.append(node.key).append(' ');
                    node = node.next;
                }
                output.append('\n');
            }
        }

        System.out.print(output);
    }
}

class HashTable<K, V> {

    Node<K, V>[] table;
    private final int M;

    @SuppressWarnings("unchecked")
    public HashTable(int M) {
        this.M = M;
        table = (Node<K, V>[]) new Node[M];
    }

    static class Node<K, V> {
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
}

class StringWrapper {
    private final String string;
    private static final int X = 263;
    private static final int P = 1_000_000_007;

    public StringWrapper(String string) {
        this.string = string;
    }

    @Override
    public int hashCode() {
        int hash = string.charAt(0);
        long x = X;

        for (int i = 1; i < string.length(); i++) {
            int product = (int) ((string.charAt(i) * x) % P);
            hash = (hash + product) % P;
            x = (x * 263) % P;
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return string.equals(obj.toString());
    }

    @Override
    public String toString() {
        return string;
    }
}
