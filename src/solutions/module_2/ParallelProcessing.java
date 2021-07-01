package solutions.module_2;

import java.util.Comparator;
import datastructures.PriorityQueue;
import java.util.Scanner;

// input:
// 2 5
// 1 2 3 4 5

// output:
// 0 0
// 1 0
// 0 1
// 1 2
// 0 4
public class ParallelProcessing {
    private static class Processor {
        private final int id;
        private long availableTime;

        public static final Comparator<Processor> PROCESSOR_COMPARATOR = (o1, o2) -> {
            int timeCompare = Long.compare(o2.availableTime, o1.availableTime);
            if (timeCompare == 0) {
                return Integer.compare(o2.id, o1.id);
            }
            return timeCompare;
        };

        public Processor(int id, int availableTime) {
            this.id = id;
            this.availableTime = availableTime;
        }

        public void process(int processTime) {
            availableTime += processTime;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int processorNum = sc.nextInt();

        PriorityQueue<Processor> processors = new PriorityQueue<>(Processor.PROCESSOR_COMPARATOR);
        for (int i = 0; i < processorNum; i++) {
            processors.add(new Processor(i, 0));
        }

        int taskNum = sc.nextInt();

        StringBuilder outputSB = new StringBuilder();
        for (int i = 0; i < taskNum; i++) {
            Processor p = processors.poll();

            outputSB.append(p.id)
                    .append(' ')
                    .append(p.availableTime)
                    .append('\n');

            p.process(sc.nextInt());
            processors.add(p);
        }

        System.out.print(outputSB);
    }
}
