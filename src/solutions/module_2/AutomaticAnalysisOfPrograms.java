package solutions.module_2;

import datastructures.DisjointSet;

import java.util.Scanner;

public class AutomaticAnalysisOfPrograms {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int varN = sc.nextInt();
        DisjointSet disjointSet = new DisjointSet(varN + 1);
        int equality = sc.nextInt();
        int inequality = sc.nextInt();

        for (int i = 0; i < equality; i++) {
            disjointSet.union(sc.nextInt(), sc.nextInt());
        }

        int res = 1;
        for (int i = 0; i < inequality; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (disjointSet.connected(a, b)) {
//                System.out.println(a + " != " + b);
                res = 0;
            }
        }
        System.out.println(res);
    }
}
