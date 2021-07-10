package solutions.module_4;

import datastructures.SplayTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SegmentSum {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opn = Integer.parseInt(reader.readLine());

        SplayTree tree = new SplayTree();
        long sum = 0;

        for (int i = 0; i < opn; i++) {
            String[] strings = reader.readLine().split(" ");
            char cmd = strings[0].charAt(0);
            if (cmd == '?') {
                // Find strings[1]
                if (tree.contains(f(Integer.parseInt(strings[1]), sum))) {
                    System.out.println("Found");
                } else {
                    System.out.println("Not found");
                }
            } else if (cmd == '+') {
                // Add strings[1]
                tree.put(f(Integer.parseInt(strings[1]), sum));
            } else if (cmd == '-') {
                // Delete string[1]
                tree.remove(f(Integer.parseInt(strings[1]), sum));
            } else if (cmd == 's') {
                // Calc sum command from segment (strings[1], strings[2])
                int l = f(Integer.parseInt(strings[1]), sum);
                int r = f(Integer.parseInt(strings[2]), sum);
//                System.out.println("l " + l + " r " + r);
                sum = tree.computeSum(l, r);
                System.out.println(sum);
            }
        }
    }

    private static int f(long x, long s) {
        // f(x) = (x + s) mod 1 000 000 001
        return (int) ((x + s) % 1_000_000_001);
    }
}
