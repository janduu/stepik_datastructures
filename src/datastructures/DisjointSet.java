package datastructures;

public class DisjointSet {
    private final int[] parent;
    private final int[] rank;

    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        }

        if (rank[aRoot] > rank[bRoot]) {
            parent[bRoot] = aRoot;
        } else {
            parent[aRoot] = bRoot;
            if (rank[aRoot] == rank[bRoot]) {
                rank[aRoot]++;
            }
        }

        return true;
    }

    public int find(int i) {
        if (i != parent[i]) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }
}
