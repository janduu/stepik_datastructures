package solutions.module_3;

import datastructures.HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PhoneBook {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        HashTable<Integer, String> phoneBook = new HashTable<>();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++)  {
            String[] strings = br.readLine().split("\\W+");

            String cmd = strings[0];
            int phone = Integer.parseInt(strings[1]);

            if ("add".equals(cmd)) {
                phoneBook.put(phone, strings[2]);
            } else if ("find".equals(cmd)) {
                if (phoneBook.contains(phone)) {
                    output.append(phoneBook.get(phone)).append('\n');
                } else {
                    output.append("not found\n");
                }
            } else if ("del".equals(cmd)) {
                phoneBook.remove(phone);
            }
        }

        System.out.print(output);
    }
}
