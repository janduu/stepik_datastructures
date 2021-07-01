package solutions.module_1;

import datastructures.PriorityQueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class HeapBuilder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        Integer[] heap = new Integer[len];

        for (int i = 0; i < len; i++) {
            heap[i] = sc.nextInt();
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(heap, Comparator.reverseOrder());
        List<String> swapLogs = queue.swapLogs;
        System.out.println(swapLogs.size());

        for (String swap : swapLogs) {
            System.out.println(swap);
        }
    }
}
