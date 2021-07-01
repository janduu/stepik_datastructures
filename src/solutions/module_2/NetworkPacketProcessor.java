package solutions.module_2;

import datastructures.Queue;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class NetworkPacketProcessor {

    private final int buffSize;
    private final Queue<Integer> processEndTime;
    private int time = Integer.MIN_VALUE;

    public NetworkPacketProcessor(int buffSize) {
        this.buffSize = buffSize;
        this.processEndTime = new Queue<>();
    }

    public void process(int[] arrivals, int[] durations) {

        int n = arrivals.length;

        for (int i = 0; i < n; i++) {
            if (processEndTime.size() < buffSize) {
                acceptPacket(arrivals[i], durations[i]);
            } else if (processEndTime.peek() > arrivals[i]){
                dropPacket();
            } else {
                processEndTime.poll();
                acceptPacket(arrivals[i], durations[i]);
            }
        }
    }

    private void acceptPacket(int arrival, int duration) {
        time = Math.max(time, arrival) + duration;
        System.out.println(time - duration);
        processEndTime.add(time);
    }

    private void dropPacket() {
        System.out.println(-1);
    }

    public static void main(String[] args) {

        byte[] in = ("""
                3 6
                0 7
                0 0
                2 0
                3 3
                4 0
                5 0""").getBytes();

        Scanner sc = new Scanner(new ByteArrayInputStream(in));
        int buffSize = sc.nextInt();
        int packetNumber = sc.nextInt();
        int[] arrivals = new int[packetNumber];
        int[] durations = new int[packetNumber];

        for (int i = 0; i < packetNumber; i++) {
            arrivals[i] = sc.nextInt();
            durations[i] = sc.nextInt();
        }

        NetworkPacketProcessor processor = new NetworkPacketProcessor(buffSize);
        processor.process(arrivals, durations);
    }
}
