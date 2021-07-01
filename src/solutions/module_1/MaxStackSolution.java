package solutions.module_1;

import datastructures.MaxStack;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaxStackSolution {
    public static void main(String[] args) throws IOException {
        byte[] in = """
                6
                push 7
                push 1
                push 7
                max
                pop
                max""".getBytes();

        MaxStack<Integer> stack = new MaxStack<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(in)));

        int opN = Integer.parseInt(reader.readLine());

        for (int i = 0; i < opN; i++) {
            String command = reader.readLine();

            if ("max".equals(command)) {
                System.out.println(stack.max());
            } else if ("pop".equals(command)) {
                stack.pop();
            } else {
                int value = Integer.parseInt(command.substring(5));
                stack.push(value);
            }
        }
    }
}
