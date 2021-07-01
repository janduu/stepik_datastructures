package solutions.module_2;

import datastructures.DisjointSet;

import java.util.Scanner;

public class TablesJoin {
    public static void main(String[] args) {
        StringBuilder outputSB = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        int tableNum = sc.nextInt();
        int joinNum = sc.nextInt();
        int max = 0;

        DisjointSet disjointSet = new DisjointSet(tableNum);
        int[] size = new int[tableNum];

        for (int i = 0; i < tableNum; i++) {
            size[i] = sc.nextInt();
            max = Math.max(max, size[i]);
        }

        for (int i = 0; i < joinNum; i++) {
            int destination = sc.nextInt() - 1;
            int source = sc.nextInt() - 1;

            int dRoot = disjointSet.find(destination);
            int sRoot = disjointSet.find(source);

            boolean joined = disjointSet.union(dRoot, sRoot);

            if (joined) {
                int root = disjointSet.find(dRoot);
                size[root] = size[dRoot] + size[sRoot];
                max = Math.max(max, size[root]);
            }
            outputSB.append(max).append('\n');
        }
        System.out.print(outputSB);
    }
}
