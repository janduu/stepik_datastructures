package solutions.module_1;

import datastructures.Stack;

public class BracketsSequenceCorrectness {
    public static int indexOfWrongBracket(String code) {
        Stack<Character> stack = new Stack<>();
        int firstIndex = 0;
        for (int i = 0; i < code.length(); i++) {

            char ch = code.charAt(i);

            if (ch == '[' || ch == '(' || ch == '{') {

                if (stack.isEmpty()) {
                    firstIndex = i;
                }
                stack.push(ch);

            } else if (ch == ']' || ch == ')' || ch == '}') {

                if (stack.isEmpty()) {
                    return i;
                }

                char top = stack.pop();

                if (top == '[' && ch != ']') {
                    return i;
                } else if (top == '(' && ch != ')') {
                    return i;
                } else if (top == '{' && ch != '}') {
                    return i;
                }
            }
        }

        return stack.isEmpty() ? -1 : firstIndex;
    }

    public static void main(String[] args) {


        int index = indexOfWrongBracket("([](){([])})");
        System.out.println(index == -1 ? "Success" : index + 1);

        System.out.println(indexOfWrongBracket("([](){([])})"));
        System.out.println(indexOfWrongBracket("()[]}"));
        System.out.println(indexOfWrongBracket("{{[()]]"));
        System.out.println(indexOfWrongBracket("foo(bar);"));
        System.out.println(indexOfWrongBracket("foo(bar[i);"));
        System.out.println(indexOfWrongBracket("{[}"));
        System.out.println(indexOfWrongBracket("{"));
    }
}
