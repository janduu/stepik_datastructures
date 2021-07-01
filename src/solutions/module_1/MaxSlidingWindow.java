package solutions.module_1;

import datastructures.MaxQueue;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class MaxSlidingWindow {
    public static void main(String[] args) {
        byte[] input = "8\n2 7 3 1 5 2 6 2\n4".getBytes();
        Scanner sc = new Scanner(new ByteArrayInputStream(input));
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        int m = sc.nextInt();

        printMaxSlidingWindow(array, m);
    }

    public static void printMaxSlidingWindow(int[] array, int m) {
        MaxQueue<Integer> queue = new MaxQueue<>();
        for (int i = 0; i < m; i++) {
            queue.add(array[i]);
        }

        System.out.println(queue.max());

        for (int i = m; i < array.length; i++) {
            queue.poll();
            queue.add(array[i]);
            System.out.println(queue.max());
        }
    }
}
