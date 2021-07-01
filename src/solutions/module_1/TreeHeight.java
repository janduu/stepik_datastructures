package solutions.module_1;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class TreeHeight {
    static byte[] input1 = "10\n9 7 5 5 2 9 9 9 2 -1".getBytes();
    static byte[] input2 = "5\n-1 0 4 0 3".getBytes();
    static byte[] input3 = "5\n4 -1 4 1 1".getBytes();

    public static int getHeight(int[] tree) {

        int maxDepth = 0;
        int[] nodeHeight = new int[tree.length];

        for (int i = 0; i < tree.length; i++) {

            int parent = tree[i];
            int parentsCount = 1;

            while (parent != -1) {
                if (nodeHeight[parent] > 0) {
                    parentsCount += nodeHeight[parent];
                    break;
                } else {
                    parent = tree[parent];
                    parentsCount++;
                }
            }

            nodeHeight[i] = parentsCount;
            maxDepth = Math.max(maxDepth, parentsCount);
        }

        return maxDepth;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(new ByteArrayInputStream(input2));
        int[] tree = new int[sc.nextInt()];

        for (int i = 0; i < tree.length; i++) {
            tree[i] = sc.nextInt();
        }

        System.out.println(Arrays.toString(tree));
        System.out.println(getHeight(tree));
    }
}
